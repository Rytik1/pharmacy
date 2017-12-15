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
import java.util.ArrayList;
import java.util.List;


public class BasketCommand extends AbstractCommand {
    private static final String LOGIN_ATTRIBUTE = "user";

    //go to basket page
    public RequestResult execute() throws CommandException {
        String login = getContent().getSessionAttribute(LOGIN_ATTRIBUTE);
        String page = ConfigurationManager.getProperty("path.page.basket");
        List<Order> listOrder = new ArrayList<>();
        OrderLogic orderLogic = new OrderLogic();

        try {
            listOrder = orderLogic.selectOrder(login);
            if (listOrder.size() != 0) {
                double sum = 0;

                /*
                for (Order order : listOrder) {
                    sum += order.getCount() * order.getPrice();
                }
                */

                sum = listOrder.stream().map(Order -> Order.getCount() * Order.getPrice()).
                        reduce((aDouble, aDouble2) -> aDouble + aDouble2).get();

                BigDecimal commonSum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_DOWN);

                getContent().setRequestAttribute("commonSUM", commonSum);
                getContent().setRequestAttribute("listOrder", listOrder);
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with basket command: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}



