package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GameCommand extends AbstractCommand {

    //go to Game coin  page

    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.game");
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
