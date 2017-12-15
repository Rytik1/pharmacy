package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.HistoryBuying;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.MessageManager;

import java.util.ArrayList;
import java.util.List;

public class HistoryBuyingCommand extends AbstractCommand {
    private static final String LOGIN_PARAM = "user";

    //get history information about buying order
    public RequestResult execute() throws CommandException {
        String login = getContent().getSessionAttribute(LOGIN_PARAM);
        String page = getContent().getRequestParameter("page");
        CommonLogic commonLogic = new CommonLogic();
        List<HistoryBuying> historyBuyingList = new ArrayList<>();
        try {
            //different Historylist to administrtor and user
            if (page.equals("/jsp/admin/administrator.jsp")) {
                historyBuyingList = commonLogic.getListHistory();
            } else {
                historyBuyingList = commonLogic.getListHistory(login);
            }
            if (historyBuyingList.size() != 0) {
                getContent().setRequestAttribute("historyList", historyBuyingList);
            } else {
                getContent().setRequestAttribute("historyListEmpty", MessageManager.getProperty("message.historyListEmpty"));
            }

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with history buying command: ", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);
    }
}
