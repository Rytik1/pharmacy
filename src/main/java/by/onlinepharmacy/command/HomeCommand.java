package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class HomeCommand extends AbstractCommand{
    //go Home page method
    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.main");
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
