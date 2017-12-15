package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AuthenticationLogic;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;
import by.onlinepharmacy.util.hash.HashUtil;

public class ChangeUserPassCommand extends AbstractCommand {
    private static final String LOGIN_PARAM = "login";
    private static final String NEW_PASSWORD_PARAM = "password";
    private static final String CONFIRM_PASSWORD_PARAM = "confirmPassword";
    private static final String OLD_PASSWORD_PARAM = "oldpassword";

    //change user password
    @Override
    public RequestResult execute() throws CommandException {
        String login = getContent().getRequestParameter(LOGIN_PARAM);
        String newPassword = getContent().getRequestParameter(NEW_PASSWORD_PARAM);
        String oldPassword = getContent().getRequestParameter(OLD_PASSWORD_PARAM);
        String confirmPassword = getContent().getRequestParameter(CONFIRM_PASSWORD_PARAM);
        String page = ConfigurationManager.getProperty("path.page.update_user");

        CommonLogic commonLogic = new CommonLogic();
        AuthenticationLogic authenticationLogic = new AuthenticationLogic();
        try {
            if (!authenticationLogic.checkLoginPasswordMatch(login, oldPassword)) {
                getContent().setRequestAttribute("oldpasswordwrong", MessageManager.getProperty("message.oldpasswordwrong"));
                getContent().setRequestAttribute("wrongChangePass", MessageManager.getProperty("message.wrongChangePass"));

            } else if (!authenticationLogic.isPasswordValid(newPassword)) {
                getContent().setRequestAttribute("passwordwrong", MessageManager.getProperty("message.passwordwrong"));
                getContent().setRequestAttribute("wrongChangePass", MessageManager.getProperty("message.wrongChangePass"));

            } else if (!confirmPassword.equals(newPassword)) {
                getContent().setRequestAttribute("wrongConfirm", MessageManager.getProperty("message.wrongConfirm"));
                getContent().setRequestAttribute("wrongChangePass", MessageManager.getProperty("message.wrongChangePass"));

            } else {
                String cryptoPassword = HashUtil.computeHash(newPassword);
                authenticationLogic.updatePassword(login, cryptoPassword);
                getContent().setRequestAttribute("goodPassUpdate", MessageManager.getProperty("message.goodPassUpdate"));
                User user = commonLogic.findUser(login);
                getContent().setSessionAttribute("users", user);
                getContent().setSessionAttribute("gender", user.getGender().toString());
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem update user information: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
