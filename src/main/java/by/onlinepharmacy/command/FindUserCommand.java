package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.logic.ImplLogic.AuthenticationLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.util.List;


public class FindUserCommand extends AbstractCommand {
    private static final String LOGIN_PARAM = "login";

    //find user  by any letter in find form
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.administrator");
        String login = getContent().getRequestParameter(LOGIN_PARAM);
        AdministratorLogic administratorLogic = new AdministratorLogic();
        AuthenticationLogic authenticationLogic = new AuthenticationLogic();
        try {
            if (!authenticationLogic.isLoginValid(login)) {
                getContent().setRequestAttribute("loginerrorenter", MessageManager.getProperty("message.loginerrorenter"));

            } else {
                List<User> userList = administratorLogic.findUser(login);

                if (userList.size() == 0) {
                    getContent().setRequestAttribute("notLogin", MessageManager.getProperty("message.notLogin"));
                } else {
                    getContent().setRequestAttribute("user_list", userList);
                }
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with find user command: ", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);
    }
}



