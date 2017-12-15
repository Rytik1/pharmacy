package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GoToDeleteUserCommand extends AbstractCommand {

    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.delete_user");
        return new RequestResult(page, NavigationType.FORWARD);
    }


}

