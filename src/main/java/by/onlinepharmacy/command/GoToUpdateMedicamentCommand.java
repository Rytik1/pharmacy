package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GoToUpdateMedicamentCommand extends AbstractCommand {
    private static final String ID_PARAM = "id";

    //go to update medicament page
    public RequestResult execute( ) throws CommandException  {
        int id = Integer.parseInt(getContent().getRequestParameter(ID_PARAM));
        AdministratorLogic administratorLogic = new AdministratorLogic();

        try {
            Medicament medicament = administratorLogic.getMedicamentByID(id);
            getContent().setRequestAttribute("medicament", medicament);
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem go to update command", e);
        }

        String page = ConfigurationManager.getProperty("path.page.update_medicament");
        return new RequestResult(page, NavigationType.FORWARD);
    }


}

