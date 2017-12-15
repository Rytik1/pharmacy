package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;


public class GoToDeleteMedicamentCommand extends AbstractCommand {

    //go to delete medicament page
    public RequestResult execute( ) {
        String page = ConfigurationManager.getProperty("path.page.delete_medicament");
        return new RequestResult(page, NavigationType.FORWARD);
    }


}



