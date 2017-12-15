package by.onlinepharmacy.logic;

import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.dao.MedicamentDAO;
import by.onlinepharmacy.dao.OrderDAO;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19.07.2017.
 */
public class OrderLogic implements IUserReceiver{
    private RequestContent content;


    public OrderLogic(RequestContent content) {
        this.content = content;
    }


    public static void addNewOrder(int medicamentID, double count, String userLogin, double countResult  ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();

            try (ProxyConnection connection = pool.getConnection()) {
                OrderDAO orderDAO=new OrderDAO(connection);
                MedicamentDAO medicamentDAO=new MedicamentDAO(connection);
                Order order = new Order();
                order.setMedicamentID(medicamentID);
                 order.setCount(count);
                 order.setUserLogin(userLogin);

                 medicamentDAO.updateCount(medicamentID,countResult);
                 orderDAO.insert(order);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new Order: ", e);
        }

    }


    public static void selectOrder(String login ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();

            try (ProxyConnection connection = pool.getConnection()) {
                OrderDAO orderDAO=new OrderDAO(connection);

                MedicamentDAO medicamentDAO=new MedicamentDAO(connection);

                String id=orderDAO.selectUserIDByLogin(login).get();
                List<Order> listOrder=orderDAO.selectMedicamentOrder(id);

                for(Order order:listOrder){
                    int medicamentId=order.getMedicamentID();
                    medicamentDAO.addInformationToOrder(order, medicamentId);
                }


        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new Order: ", e);
        }

    }


}
