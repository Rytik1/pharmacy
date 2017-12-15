package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GoToRegistrationCommand extends AbstractCommand {

    //go to registartion page
    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
