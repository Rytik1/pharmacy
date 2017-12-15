package by.onlinepharmacy.logic.ImplLogic;

import by.onlinepharmacy.dao.*;
import by.onlinepharmacy.entity.*;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.logic.IOrderLogic;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;

import java.util.*;


public class OrderLogic implements IOrderLogic {
    private static final String COUNT_ORDER_REGEX = "^[0-9]{1,7}[.]?[0]?";

    //check count order to REGEX
    public boolean isCountValid(String count) {
        return (count != null) && count.matches(COUNT_ORDER_REGEX);
    }

    //add new order to db
    @Override
    public void addNewOrder(int medicamentID, double count, String userLogin, double countResult) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            Order order = new Order();
            order.setMedicamentID(medicamentID);
            order.setCount(count);
            order.setUserLogin(userLogin);

            medicamentDAO.updateCount(medicamentID, countResult);
            orderDAO.insert(order);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new Order: ", e);
        }

    }

    //find order by user login in db
    @Override
    public List<Order> selectOrder(String login) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Order> listOrder;
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            String id = orderDAO.selectUserIDByLogin(login).get();
            listOrder = orderDAO.selectMedicamentOrder(id);

            for (Order order : listOrder) {
                int medicamentId = order.getMedicamentID();
                medicamentDAO.addInformationToOrder(order, medicamentId);
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem find order by user login in db ", e);
        }
        return listOrder;
    }

    //delete order in DB
    @Override
    public List<Order> deleteOrder(int orderId, double count, String login, int medicamentID) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Order> listOrder = new ArrayList<>();
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.deleteOrderById(orderId);

            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            double oldCount = medicamentDAO.selectOldCountMedicament(medicamentID);
            double actualCount = count + oldCount;
            medicamentDAO.updateCount(medicamentID, actualCount);
            String id = orderDAO.selectUserIDByLogin(login).get();
            listOrder = orderDAO.selectMedicamentOrder(id);

            for (Order order : listOrder) {
                int medicamentId = order.getMedicamentID();
                medicamentDAO.addInformationToOrder(order, medicamentId);
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem delete order in DB ", e);
        }
        return listOrder;
    }


    //delete all order in DB by user Login
    @Override
    public void deleteALLOrderByLogin(String login) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.deleteOrdersByLohin(login);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem delete all order in DB by user Login: ", e);
        }
    }


    //select buying information to DB
    public void sendBuyingInfToHistory(List<Order> orderList, String login) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            BuyingHistoryDAO historyDAO = new BuyingHistoryDAO(connection);
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);

            for (Order order : orderList) {
                int userId = Integer.parseInt(orderDAO.selectUserIDByLogin(login).get());
                HistoryBuying historyBuying = new HistoryBuying();
                historyBuying.setCountByuing(order.getCount());
                GregorianCalendar calendar = new GregorianCalendar();
                Date date = calendar.getTime();
                historyBuying.setDateBuying(date);
                historyBuying.setMedicamentId(order.getMedicamentID());
                historyBuying.setUserId(userId);
                int id = order.getMedicamentID();
                String name = medicamentDAO.selectNamebyID(id);
                if (name != null) {
                    historyBuying.setName(name);
                }
                historyDAO.insert(historyBuying);
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem select buying information to DB  : ", e);
        }
    }

    //check user recipe
    @Override
    public Optional<List<Recipe>> checkRecipe(String login, int medcamentId) throws BusinessLogicException {
        Optional<List<Recipe>> recipeList;
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Order> listOrder;
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            RecipeDAO recipeDAO = new RecipeDAO(connection);
            User user = userDAO.selectUserByLoginCut(login);
            String numberRecype = user.getNumberReceipt();
            recipeList = recipeDAO.selectRecipeByUserId(numberRecype, medcamentId);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check Recipe: ", e);
        }
        return recipeList;
    }


    //select count medicament information from DB
    public double getActualCountMedicament(int medicamentdID) throws BusinessLogicException {
        double count = 0;

        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                count = medicamentDAO.selectOldCountMedicament(medicamentdID);
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem select actual count from DB  : ", e);
        }
        return count;

    }

    //insert information about request from client about new medicament in the pharmacy
    public void sendReqInfFromClient(String login, int medicamentID) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            UserDAO userDAO = new UserDAO(connection);
            int userId = userDAO.selectIdByLogin(login);
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.insertRequest(userId, medicamentID);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem select buying information to DB  : ", e);
        }
    }


    //find list user email to send about new count medicament by medID

    public List<String> selectEmailByMedId(int medicamentId) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<String> listEmail;
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            listEmail = orderDAO.selectRequestMedMail(medicamentId);


        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem find order by user login in db ", e);
        }
        return listEmail;
    }

    //delete user request about new count medicament by medID

    public void DeleteUserReqByMedId(int medicamentId) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.deleteUserReqByMedID(medicamentId);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem find order by user login in db ", e);
        }

    }

}
