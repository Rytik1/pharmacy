package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.logic.ImplLogic.OrderLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.util.List;

public class RequestInformationMedCountCommand extends AbstractCommand{
    private static final String MEDICAMENT_ID_PARAM = "id";
    private static final String LOGIN_ATRIBUTE = "user";

    //send to db request from user about appearance new count medicament
    @Override
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.main");
        String login = getContent().getSessionAttribute(LOGIN_ATRIBUTE);
        int medicamentID = Integer.parseInt(getContent().getRequestParameter(MEDICAMENT_ID_PARAM));
        OrderLogic orderLogic = new OrderLogic();
        CommonLogic commonLogic = new CommonLogic();

        try {
            orderLogic.sendReqInfFromClient(login,medicamentID);
            getContent().setSessionAttribute("sendEmail", MessageManager.getProperty("message.sendEmail"));
            List<Medicament> medicamentList = commonLogic.selectAllMedicament();
            if(medicamentList.size()!=0) {
                getContent().setRequestAttribute("findmedicamentList", medicamentList);
            }
            } catch (BusinessLogicException e ) {
                 throw new CommandException("Problem with do order command: ", e);
            }

        return new RequestResult(page, NavigationType.FORWARD);

     }
}
