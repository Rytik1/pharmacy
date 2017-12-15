package by.onlinepharmacy.logic;

import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.dao.MedicamentDAO;
import by.onlinepharmacy.dao.UserDAO;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

/**
 * Created by User on 17.07.2017.
 */
public class AdministratorLogic implements IUserReceiver {

    private RequestContent content;

    public AdministratorLogic(RequestContent content) {
        this.content = content;
    }




    public static void addNewMedicament(String name, String dosage, double amountInStock, String country ,
                                  double price , boolean recipeRequired ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                Medicament  medicament = new Medicament();
                 medicament.setName(name);
                medicament.setDosage(dosage);
                medicament.setAmountInStock(amountInStock);
                medicament.setCountry(country);
                medicament.setPrice(price);
                medicament.setRecipeRequired(recipeRequired);
                medicamentDAO.insert(medicament);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new medicament: ", e);
        }

    }

    public static void updateMedicament(int id,String name, String dosage, double amountInStock, String country ,
                                        double price , boolean recipeRequired ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                Medicament  medicament = new Medicament();
                medicament.setId(id);
                medicament.setName(name);
                medicament.setDosage(dosage);
                medicament.setAmountInStock(amountInStock);
                medicament.setCountry(country);
                medicament.setPrice(price);
                medicament.setRecipeRequired(recipeRequired);
                medicamentDAO.update(medicament);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }

    }

    public static void deleteMedicament(String id ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                medicamentDAO.delete(id);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }

    }


    public static void deleteUser(String id ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                UserDAO userDAO=new UserDAO(connection);
                userDAO.delete(id);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }

    }


    public static List<User> selectAllUser( ) throws BusinessLogicException {
        List<User> userList ;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            UserDAO userDAO = new UserDAO(connection);
            userList = userDAO.selectAll();
            if (userList!=null) {
                userList.sort(Comparator.comparing(User::getName));
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return userList ;
    }

    public static  List<User> findUser(String login ) throws BusinessLogicException {
        List<User> usertList ;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            UserDAO userDAO=new UserDAO(connection);

            usertList = userDAO.selectUserByLogin(login);
            if (usertList!=null) {
                usertList.sort(Comparator.comparing(User::getLogin));
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return usertList ;
    }

}
