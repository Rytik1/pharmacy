package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;

import java.util.List;

public class ShowAllUserCommand extends AbstractCommand {

    //get all user info
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.administrator");
        try {
            AdministratorLogic administratorLogic = new AdministratorLogic();
            List<User> userList = administratorLogic.selectAllUser();

            if (userList.size() != 0) {
                getContent().setRequestAttribute("user_list", userList);
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem show all users: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}

