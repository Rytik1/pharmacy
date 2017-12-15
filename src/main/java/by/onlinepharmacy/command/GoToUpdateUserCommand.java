package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GoToUpdateUserCommand extends AbstractCommand {
    private static final String LOGIN_PARAM = "user";

    //update user info method
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.update_user");
        String login = getContent().getSessionAttribute(LOGIN_PARAM);
        CommonLogic commonLogic = new CommonLogic();
        try {
            //find user by login and send it to attribute
            User user = commonLogic.findUser(login);
            getContent().setSessionAttribute("users", user);
            getContent().setSessionAttribute("gender", user.getGender().toString());

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with go to update user command: ", e);

        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}


