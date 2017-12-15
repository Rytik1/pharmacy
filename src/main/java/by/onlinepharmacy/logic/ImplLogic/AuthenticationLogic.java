package by.onlinepharmacy.logic.ImplLogic;


import by.onlinepharmacy.dao.UserDAO;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.logic.IAuthenticationLogic;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;
import by.onlinepharmacy.util.GenderType1;
import by.onlinepharmacy.util.date.GenderType;
import by.onlinepharmacy.util.hash.HashUtil;
import by.onlinepharmacy.util.date.UserType;

import java.sql.Date;
import java.util.Optional;

public class AuthenticationLogic implements IAuthenticationLogic {

    private static final String EMAIL_REGEX = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    private static final String LOGIN_REGEX = "[\\w-]{3,20}";
    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\\W|_]).{4,20}";
    private static final String NUMBER_RECIPE_REGEX = "[\\d]{4}";


    //check login to REGEX
    public boolean isLoginValid(final String login) {
        return (login != null) && login.matches(LOGIN_REGEX);
    }

    //check NumberRecipe to REGEX
    public boolean isNumberRecipeValid(final String numberRecipe) {
        return (numberRecipe != null) && numberRecipe.matches(NUMBER_RECIPE_REGEX);
    }

    //check email to REGEX
    @Override
    public boolean isEmailValid(final String email) {
        return (email != null) && email.matches(EMAIL_REGEX);
    }

    //check Password to REGEX
    public boolean isPasswordValid(final String password) {
        return (password != null) && password.matches(PASSWORD_REGEX);
    }

    //check Password and login with DB
    @Override
    public boolean checkLoginPasswordMatch(String enterLogin, String enterPassword) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            Optional<String> passwordOptional = userDAO.selectPassword(enterLogin);
            if (passwordOptional.isPresent()) {
                String password = passwordOptional.get();
                result = password.equals(HashUtil.computeHash(enterPassword));

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check the user's existence: ", e);
        }
        return result;
    }

    //select role in DB by login
    @Override
    public UserType checkRoleByLogin(String enterLogin) throws BusinessLogicException {
        UserType role = UserType.GUEST;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            role = userDAO.selectRoleByLogin(enterLogin).get();


        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check the user's existence: ", e);
        }
        return role;
    }

    //check login exist in DB
    @Override
    public boolean checkLoginExist(String enterLogin) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            Optional<String> loginOptional = userDAO.selectLogin(enterLogin);
            if (loginOptional.isPresent()) {
                result = true;
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return result;
    }

    //check enter Email exist in DB
    @Override
    public boolean checkEmailExist(String enterEmail) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            UserDAO userDAO = new UserDAO(connection);
            Optional<String> emailOptional = userDAO.selectEmailByItself(enterEmail);
            if (emailOptional.isPresent()) {
                result = true;
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of email: ", e);
        }
        return result;
    }

    //add new user to DB
    @Override
    public void addNewUser(String login, String email, String cryptoPassword, String name,
                           String surname, Date birthDate, String city, String sex, String recipe)
            throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User();
            user.setLogin(login);
            user.setPassword(cryptoPassword);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthDate(birthDate);
            user.setNumberReceipt(recipe);
            user.setCity(city);
            user.setGender(GenderType1.valueOf(sex.toUpperCase()));
            userDAO.insert(user);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new user: ", e);
        }
    }

    //update  user information in DB
    @Override
    public void updateUser(String login, String email, String name,
                           String surname, Date birthDate, String city, String sex, String recipe)
            throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthDate(birthDate);
            user.setCity(city);
            user.setNumberReceipt(recipe);
            user.setGender(GenderType1.valueOf(sex.toUpperCase()));
            userDAO.update(user);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new user: ", e);
        }
    }

    //update  user password in DB
    public void updatePassword(String login, String cryptoPassword)
            throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            userDAO.updatePassword(login, cryptoPassword);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new user: ", e);
        }
    }

    //check recipe number exist in DB

    public boolean checkRecipeExist(String recipe) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            Optional<String> recipeOptional = userDAO.selectRecipe(recipe);
            if (recipeOptional.isPresent()) {
                result = true;
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check recipe: ", e);
        }
        return result;
    }

}

