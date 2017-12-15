package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AuthenticationLogic;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.sql.Date;


public class UserUpdateInformationCommand extends AbstractCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String EMAIL_PARAM = "email";
    private static final String SURNAME_PARAM = "surname";
    private static final String NAME_PARAM = "name";
    private static final String BIRTH_PARAM = "birthDate";
    private static final String CITY_PARAM = "city";
    private static final String SEX_PARAM = "sex";
    private static final String RECIPE_PARAM = "recipe";

    //update user info
    public RequestResult execute() throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.update_user");
        String login = getContent().getRequestParameter(LOGIN_PARAM);
        String email = getContent().getRequestParameter(EMAIL_PARAM);
        String surname = getContent().getRequestParameter(SURNAME_PARAM);
        String name = getContent().getRequestParameter(NAME_PARAM);
        Date birthDate = Date.valueOf(getContent().getRequestParameter(BIRTH_PARAM));
        String city = getContent().getRequestParameter(CITY_PARAM);
        String sex = getContent().getRequestParameter(SEX_PARAM);
        String numberRecipe = getContent().getRequestParameter(RECIPE_PARAM);

        CommonLogic commonLogic = new CommonLogic();
        AuthenticationLogic authenticationLogic = new AuthenticationLogic();


        //check new information to validate REGEX
       if (!authenticationLogic.isNumberRecipeValid(numberRecipe)) {
            getContent().setRequestAttribute("numberwrong", MessageManager.getProperty("message.numberwrong"));
        } else {
            try {

                authenticationLogic.updateUser(login, email, name, surname, birthDate, city, sex, numberRecipe);
                getContent().setRequestAttribute("goodUpdate", MessageManager.getProperty("message.goodUpdate"));

                User user = commonLogic.findUser(login);
                getContent().setSessionAttribute("users", user);
                getContent().setSessionAttribute("gender", user.getGender().toString());

            } catch (BusinessLogicException e) {
                throw new CommandException("Problem update user information: ", e);
            }

        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}




