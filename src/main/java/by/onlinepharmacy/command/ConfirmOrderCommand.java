package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.OrderLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;
import java.util.ArrayList;
import java.util.List;


public class ConfirmOrderCommand extends AbstractCommand {

    private static final String LOGIN_ATTRIBUTE = "user";

    //confirm order and clear information about order in DB. Send information about order to history
    public RequestResult execute() throws CommandException {
        String login = getContent().getSessionAttribute(LOGIN_ATTRIBUTE);
        String page = ConfigurationManager.getProperty("path.page.basket");
        List<Order> listOrder = new ArrayList<>();
        OrderLogic orderLogic = new OrderLogic();

        try {
            listOrder = orderLogic.selectOrder(login);

            if (listOrder.size() == 0) {
                getContent().setRequestAttribute("notOrder", MessageManager.getProperty("message.notOrder"));
            } else {
                orderLogic.sendBuyingInfToHistory(listOrder, login);
                orderLogic.deleteALLOrderByLogin(login);
                getContent().setRequestAttribute("confirm", MessageManager.getProperty("message.confirm"));
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with confirm order command: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }

}




