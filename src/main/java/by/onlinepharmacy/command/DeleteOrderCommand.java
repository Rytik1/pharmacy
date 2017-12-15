package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.OrderLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class DeleteOrderCommand extends  AbstractCommand {

    private static final String LOGIN_ATTRIBUTE = "user";
    private static final String ORDER_ID_PARAMETR ="id";
    private static final String COUNT_PARAMETR = "count";
    private static final String MEDICAMENT_ID_PARAMETR = "medicamentId";

    //delete order from basket
    public RequestResult execute( ) throws CommandException {
        String page= ConfigurationManager.getProperty("path.page.basket");
        String login = getContent().getSessionAttribute(LOGIN_ATTRIBUTE);
        int orderId = Integer.parseInt(getContent().getRequestParameter(ORDER_ID_PARAMETR));
        double count = Double.parseDouble(getContent().getRequestParameter(COUNT_PARAMETR));
        int medicamentID = Integer.parseInt(getContent().getRequestParameter(MEDICAMENT_ID_PARAMETR));
        OrderLogic orderLogic=new OrderLogic();
        List<Order> listOrder;

        try {
            listOrder= orderLogic.deleteOrder(orderId,count,login,medicamentID);
            if (listOrder.size()!=0) {
                double sum = 0;

                sum = listOrder.stream().map(Order -> Order.getCount() * Order.getPrice()).
                        reduce((aDouble, aDouble2) -> aDouble + aDouble2).get();

                BigDecimal commonSum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_DOWN);

                getContent().setRequestAttribute("commonSUM", commonSum);
                getContent().setRequestAttribute("listOrder", listOrder);
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with delete order command: ", e);

         }
        return new RequestResult(page, NavigationType.FORWARD);
    }

}



