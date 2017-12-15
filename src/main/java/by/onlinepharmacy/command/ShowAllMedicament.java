package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;

import java.util.List;

public class ShowAllMedicament extends AbstractCommand {
    private static final String PAGE_PARAM = "page";

    //get all medicament info
    public RequestResult execute() throws CommandException {
        String page = getContent().getRequestParameter(PAGE_PARAM);
        CommonLogic commonLogic = new CommonLogic();
        try {
            List<Medicament> medicamentList = commonLogic.selectAllMedicament();
            if (medicamentList.size() != 0) {
                getContent().setRequestAttribute("findmedicamentList", medicamentList);
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem during show all medicament: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }

}



