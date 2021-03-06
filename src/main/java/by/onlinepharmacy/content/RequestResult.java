package by.onlinepharmacy.content;


import by.onlinepharmacy.util.date.NavigationType;

public class RequestResult {

    private String page;
    private NavigationType navigationType;

    public RequestResult(String page, NavigationType navigationType) {
        this.page = page;
        this.navigationType = navigationType;
    }

    public String getPage() {
        return page;
    }

    public NavigationType getNavigationType() {
        return navigationType;
    }
}