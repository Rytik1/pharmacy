package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;

public class GoToAddMedicamentCommand extends AbstractCommand {

    //go to add medicament page
    public RequestResult execute() {
        String page = ConfigurationManager.getProperty("path.page.addMedicament");
        return new RequestResult(page, NavigationType.FORWARD);
    }
}
