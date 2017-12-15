package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Recipe;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.util.List;

public class ShowUserRecipeCommand extends AbstractCommand {

    private static final String LOGIN_ATTRIBUTE = "user";
    private static final String REQUEST_NEW_RECIPE_PARAM = "request";

    //get user recipe
    public RequestResult execute() throws CommandException {

        String login = getContent().getSessionAttribute(LOGIN_ATTRIBUTE);
        String requestNewRecipe = getContent().getRequestParameter(REQUEST_NEW_RECIPE_PARAM);
        String page = ConfigurationManager.getProperty("path.page.main");
        CommonLogic commonLogic = new CommonLogic();
        try {
            List<Recipe> recipeList = commonLogic.findRecipe(login);
            if (recipeList.size() != 0) {
                getContent().setRequestAttribute("recipeList", recipeList);
                if (requestNewRecipe != null) {
                    getContent().setRequestAttribute("newRecipe", MessageManager.getProperty("message.newRecipe"));
                }
            } else {
                getContent().setRequestAttribute("notRecipe", MessageManager.getProperty("message.natRecipe"));
            }

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem show user recipe : ", e);

        }

        return new RequestResult(page, NavigationType.FORWARD);
    }

}


