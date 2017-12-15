package by.onlinepharmacy.factory;


import by.onlinepharmacy.command.AbstractCommand;
import by.onlinepharmacy.command.CommandMap;
import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.controller.Controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class FactoryCommand { //FactoryMethod
    private static Logger logger = LogManager.getLogger(FactoryCommand.class);

    AbstractCommand command = null;

    public AbstractCommand initCommand(RequestContent requestContent) {
        //AbstractCommand command = null;

        try {
            //get type of command
            String commandName = requestContent.getRequestParameter("command").toUpperCase();
            command = CommandMap.defineCommandType(commandName);
            command.setContent(requestContent);

        } catch (IllegalArgumentException e) {
            logger.error("Problem with command type",e);        }
        return command;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}