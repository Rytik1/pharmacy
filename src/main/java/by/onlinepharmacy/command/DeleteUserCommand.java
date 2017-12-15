package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;
import by.onlinepharmacy.util.date.UserType;


public class DeleteUserCommand extends AbstractCommand {
    private static final String ID_PARAM = "id";
    private static final String ROLE_PARAM = "role";

    //delete user method
    public RequestResult execute() throws CommandException {
        String id = getContent().getRequestParameter(ID_PARAM);
        String page = ConfigurationManager.getProperty("path.page.delete_user");
        String role = getContent().getRequestParameter(ROLE_PARAM);
        AdministratorLogic administratorLogic = new AdministratorLogic();

        try {
            if (role.equals(UserType.ADMIN.toString())) {
                getContent().setRequestAttribute("cantDeleteAdmin", MessageManager.getProperty("message.cantDeleteAdmin"));
            } else {
                administratorLogic.deleteUser(id);
                getContent().setRequestAttribute("resultdelete", MessageManager.getProperty("message.delete_user"));
                getContent().setRequestAttribute("name", id + "was delete");
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with delete user command: ", e);

        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}

