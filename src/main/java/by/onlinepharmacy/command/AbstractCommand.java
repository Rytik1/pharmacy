package by.onlinepharmacy.command;


import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.CommandException;

//Abstract class for all command

public abstract class AbstractCommand {
    private RequestContent content;

    public void setContent(RequestContent content) {
        this.content = content;
    }

    public abstract RequestResult execute() throws CommandException;

    public RequestContent getContent() {
        return content;
    }
}
