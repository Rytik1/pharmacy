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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 18.07.2017.
 */
public class CommonLogic implements IUserReceiver {

    private RequestContent content;


    public CommonLogic(RequestContent content) {
        this.content = content;
    }

    public static  List<Medicament> selectAllMedicament( ) throws BusinessLogicException {
        List<Medicament> medicamentList ;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
              medicamentList = medicamentDAO.selectAll();
            if (medicamentList!=null) {
                medicamentList.sort(Comparator.comparing(Medicament::getName));
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return medicamentList ;
    }

    public static  List<Medicament> findMedicament(String name ) throws BusinessLogicException {
        List<Medicament> medicamentList ;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);

            medicamentList = medicamentDAO.findMedicamentByName(name);
            if (medicamentList!=null) {
                medicamentList.sort(Comparator.comparing(Medicament::getName));
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return medicamentList ;
    }

    public static   User  findUser(String login ) throws BusinessLogicException {
       User user ;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {

            UserDAO userDAO = new UserDAO(connection);

            user = userDAO.selectUserByLoginCut(login);
            if (user==null) {
                throw new BusinessLogicException("Problem with find user: ");
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem with find user: ", e);
        }
        return user ;
    }


    }
