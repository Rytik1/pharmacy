package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.resource.ConfigurationManager;

public class GameCheckNumberCommand extends AbstractCommand {

    //go to Game check number  page

    @Override
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.gameCheckNumber");
        return new RequestResult(page, NavigationType.FORWARD);

    }
}
