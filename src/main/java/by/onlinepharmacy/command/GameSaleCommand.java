package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GameSaleCommand extends AbstractCommand {

    //go to GameSale page

    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.gameSale");
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
