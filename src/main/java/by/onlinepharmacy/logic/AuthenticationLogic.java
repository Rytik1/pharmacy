package by.onlinepharmacy.logic;


import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.dao.UserDAO;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;
import by.onlinepharmacy.util.GenderType1;
import by.onlinepharmacy.util.GenderType1;
import by.onlinepharmacy.util.HashUtil;
import by.onlinepharmacy.util.UserType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.Optional;

public class AuthenticationLogic implements  IUserReceiver {

    private static final String EMAIL_REGEX = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
       private static final String LOGIN_REGEX = "[\\w-]{3,20}";
    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\\W|_]).{4,20}";
     private RequestContent content;

    public AuthenticationLogic(RequestContent content) {
        this.content = content;
    }

    public static boolean isLoginValid(final String login) {
        return (login != null) && login.matches(LOGIN_REGEX);
    }

    public static boolean isPasswordValid(final String password) {
        return (password != null) && password.matches(PASSWORD_REGEX);
    }

    public static boolean checkLoginPasswordMatch(String enterLogin, String enterPassword) throws BusinessLogicException {
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

    public static boolean checkRoleByLogin(String enterLogin) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            Optional<by.onlinepharmacy.util.date.UserType> role = userDAO.selectRoleByLogin(enterLogin);
               if (role.isPresent()&&role.get().equals(UserType.ADMIN)) {

                result=true;
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check the user's existence: ", e);
        }
        return result;
    }


    public static boolean isEmailValid(final String email) {
        return (email != null) && email.matches(EMAIL_REGEX);
    }

    public static boolean checkLoginExist(String enterLogin) throws BusinessLogicException {
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
    public static boolean checkEmailExist(String enterEmail) throws BusinessLogicException {
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







    public static void addNewUser(String login, String email, String cryptoPassword,  String name ,
           String surname , Date birthDate,    String city,  String sex  ,String recipe )
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

    public static void updateUser(String login, String email, String cryptoPassword,  String name ,
                                  String surname , Date birthDate,    String city,  String sex  ,String recipe )
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
            user.setCity(city);
            user.setNumberReceipt(recipe);
            user.setGender(GenderType1.valueOf(sex.toUpperCase()));
            userDAO.update(user);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new user: ", e);
        }
    }



   // @Override
    public void logoutLogic( ) {
//логика для выхода
    }
}
