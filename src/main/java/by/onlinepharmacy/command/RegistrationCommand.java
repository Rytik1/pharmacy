package by.onlinepharmacy.command;


import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AuthenticationLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;
import by.onlinepharmacy.util.hash.HashUtil;
import by.onlinepharmacy.util.date.UserType;

import java.sql.Date;

public class RegistrationCommand extends AbstractCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String CONFIRM_PASSWORD_PARAM = "confirmPassword";
    private static final String SURNAME_PARAM = "surname";
    private static final String NAME_PARAM = "name";
    private static final String BIRTH_PARAM = "birthDate";
    private static final String CITY_PARAM = "city";
    private static final String SEX_PARAM = "sex";
    private static final String RECIPE_PARAM = "recipe";


    //registration user method
    public RequestResult execute( ) throws CommandException {
        String page= ConfigurationManager.getProperty("path.page.registration");
        String login = getContent().getRequestParameter(LOGIN_PARAM);
        String email = getContent().getRequestParameter(EMAIL_PARAM);
        String surname = getContent().getRequestParameter(SURNAME_PARAM);
        String password = getContent().getRequestParameter(PASSWORD_PARAM);
        String confirmPassword = getContent().getRequestParameter(CONFIRM_PASSWORD_PARAM);
        String name = getContent().getRequestParameter(NAME_PARAM);
        Date birthDate = Date.valueOf(getContent().getRequestParameter(BIRTH_PARAM));
        String city = getContent().getRequestParameter(CITY_PARAM);
        String sex = getContent().getRequestParameter(SEX_PARAM);
        String numberRecipe = getContent().getRequestParameter(RECIPE_PARAM);
        AuthenticationLogic authenticationLogic=new AuthenticationLogic();

        //check information to validation REGEX
        if (!authenticationLogic.isLoginValid(login)) {
            getContent().setRequestAttribute("loginwrong", MessageManager.getProperty("message.loginwrong"));
        } else if (!authenticationLogic.isEmailValid(email)) {
            getContent().setRequestAttribute("emailwrong", MessageManager.getProperty("message.emailwrong"));
        } else if (!confirmPassword.equals(password)) {
            getContent().setRequestAttribute("wrongConfirm", MessageManager.getProperty("message.wrongConfirm"));
        } else if (!authenticationLogic.isPasswordValid(password)) {
            getContent().setRequestAttribute("passwordwrong", MessageManager.getProperty("message.passwordwrong"));
        } else if (!authenticationLogic.isNumberRecipeValid(numberRecipe)) {
            getContent().setRequestAttribute("numberwrong", MessageManager.getProperty("message.numberwrong"));
        } else {
            try {
                //check if exist login
                if (authenticationLogic.checkLoginExist(login)) {
                    getContent().setRequestAttribute("loginchange", MessageManager.getProperty("message.loginchange"));
                } else if (authenticationLogic.checkEmailExist(email)) {
                    getContent().setRequestAttribute("emailchange", MessageManager.getProperty("message.emailchange"));
                } else if (authenticationLogic.checkRecipeExist(numberRecipe)) {
                    getContent().setRequestAttribute("recipechange", MessageManager.getProperty("message.recipechange"));
                } else {
                     String cryptoPassword = HashUtil.computeHash(password);
                    //logger.info(login);
                    //registration new user in DB
                    authenticationLogic.addNewUser(login, email, cryptoPassword, name, surname, birthDate, city, sex, numberRecipe);
                    //MailUtil.sendRegistrationLink(login, email, verifyingString, language);
                    getContent().setSessionAttribute("user", login);
                    UserType role = authenticationLogic.checkRoleByLogin(login);
                    getContent().setSessionAttribute("role", role);
                     page = ConfigurationManager.getProperty("path.page.main");
                }
            } catch (BusinessLogicException e) {
               throw new CommandException("Problem during user sign up: ", e);
            }
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}
