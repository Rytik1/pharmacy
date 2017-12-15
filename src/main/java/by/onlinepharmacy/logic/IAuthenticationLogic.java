package by.onlinepharmacy.logic;

import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.util.date.UserType;

import java.sql.Date;

/**
 * Created by User on 23.07.2017.
 */
public interface IAuthenticationLogic {
    boolean checkLoginPasswordMatch(String enterLogin, String enterPassword) throws BusinessLogicException;

    UserType checkRoleByLogin(String enterLogin) throws BusinessLogicException;

    boolean isEmailValid(String email);

    boolean checkLoginExist(String enterLogin) throws BusinessLogicException;

    boolean checkEmailExist(String enterEmail) throws BusinessLogicException;

    void addNewUser(String login, String email, String cryptoPassword, String name,
                    String surname, Date birthDate, String city, String sex, String recipe)
            throws BusinessLogicException;

    void updateUser(String login, String email, String name,
                    String surname, Date birthDate, String city, String sex, String recipe)
                    throws BusinessLogicException;
}
