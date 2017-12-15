package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.resource.ConfigurationManager;

public class LogoutCommand extends AbstractCommand {

    //log out command
    public RequestResult execute() {
        String page = ConfigurationManager.getProperty("path.page.login");
        //set attr to end session in RequestContent.class
        getContent().setRequestAttribute("sessionEnd", "sessionEnd");
        return new RequestResult(page, NavigationType.REDIRECT);
    }


}
