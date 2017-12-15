package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;


public class DeleteMedicamentCommand extends AbstractCommand {


    private static final String ID_PARAM = "id";
    private static final String NAME_PARAM = "name";

    //delete medicament from base
    public RequestResult execute() throws CommandException {
        String id = getContent().getRequestParameter(ID_PARAM);
        String page = ConfigurationManager.getProperty("path.page.delete_medicament");
        String name = getContent().getRequestParameter(NAME_PARAM);
        AdministratorLogic administratorLogic = new AdministratorLogic();

        try {
            administratorLogic.deleteMedicament(id);
            getContent().setRequestAttribute("resultdelete", MessageManager.getProperty("message.delete_medicament"));
            getContent().setRequestAttribute("name", name);

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with delete medicament command: ", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);
    }


}



