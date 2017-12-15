package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.MessageManager;

import java.util.List;


public class FindMedicamentCommand extends AbstractCommand {
    private static final String NAME_PARAM = "name";
    private static final String PAGE_PARAM = "page";
    private static final String COMMAND_PARAM = "command";

    public RequestResult execute() throws CommandException {
        String command = getContent().getRequestParameter(COMMAND_PARAM);
        String page = getContent().getRequestParameter(PAGE_PARAM);
        String name = getContent().getRequestParameter(NAME_PARAM).toUpperCase();

        //attribute to DoOrderCommand method get medicamentList() to return true list
        // (list- when we see all  medicament or short list by name)
        //without this attr every time our table will hide cose  DoOrderCommand do redirect
        getContent().setSessionAttribute("findName",name);
        getContent().setSessionAttribute("findCommand",command);
         CommonLogic commonLogic = new CommonLogic();

        try { //check name to validation regex
            if (!commonLogic.isMedicamentNameValid(name)) {
                getContent().setSessionAttribute("medicamentNameFalse", MessageManager.getProperty("message.medicamentNameFalse"));
            } else {
                List<Medicament> medicamentList = commonLogic.findMedicament(name);
                getContent().setSessionAttribute("findmedicamentList", medicamentList);
                //check how many medicaments was founded
                if (medicamentList.size() == 0) {
                    getContent().setSessionAttribute("notmedicament", MessageManager.getProperty("message.notmedicament"));
                }
                System.out.println(medicamentList.size()+" size list");

            }

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with find madicament command: ", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);
    }

}



