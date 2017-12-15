package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AuthenticationLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;
import by.onlinepharmacy.util.date.UserType;


public class LoginCommand extends AbstractCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    //login method
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.login");
        String login = getContent().getRequestParameter(LOGIN_PARAM);
        String password = getContent().getRequestParameter(PASSWORD_PARAM);
        AuthenticationLogic authenticationLogic = new AuthenticationLogic();

        //check login for validation REGEX
        if (!authenticationLogic.isLoginValid(login)) {
            getContent().setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        } else if (!authenticationLogic.isPasswordValid(password)) {
            //check passvord for validation REGEX
            getContent().setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        } else try {

                //check login and pass with information in DB
                if (authenticationLogic.checkLoginPasswordMatch(login, password)) {
                    getContent().setSessionAttribute("user", login);
                    page = ConfigurationManager.getProperty("path.page.main");
                     //check user role
                    UserType role = authenticationLogic.checkRoleByLogin(login);
                    getContent().setSessionAttribute("role", role);
                    if (role.equals(UserType.ADMIN)) {
                        page = ConfigurationManager.getProperty("path.page.administrator");
                    }

                } else {
                    getContent().setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                    getContent().setRequestAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }


        } catch (BusinessLogicException e) {
            throw new CommandException("Problem login command: ", e);

        }
        return new RequestResult(page, NavigationType.FORWARD);
    }

}



