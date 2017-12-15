package by.onlinepharmacy.dao;

import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderDAO extends AbstractDAO<String, Order> {

    private static final String SQL_INSERT_INTO_BASKET =
            "INSERT INTO basket_order (medicament_id, count, user_id )" +
                    " VALUES (?, ?, ? ) ";

    private static final String SQL_SELECT_MEDICAMENT_ORDER_BY_USER_ID =
            "SELECT id,medicament_id,count FROM basket_order WHERE user_id=?";

    private static final String SQL_DELETE_MEDICAMENT_ORDERS_LOGIN =
            "DELETE  FROM basket_order WHERE user_id=?";

    private static final String SQL_DELETE_MEDICAMENT_ORDER_BY_ID =
            "DELETE  FROM basket_order WHERE id=?";

    private static final String SQL_SELECT_USER_ID_BY_LOGIN =
            "SELECT id FROM user WHERE login=?";

    private static final String SQL_INSERT_USER_REQUEST =
            "INSERT INTO request_by_user (user_id, medicament_id   )" +
                    " VALUES (?, ?  ) ";

    private static final String SQL_DELETE_USER_REQUEST_BY_MED_ID =
            "DELETE  FROM request_by_user WHERE medicament_id=?";


    private static final String SQL_SELECT_EMAIL_FROM_REQUEST_BY_USER =
            "SELECT  email FROM user AS users" +
                    " join request_by_user AS req " +
                    "ON users.id=req.user_id" +
                    " WHERE  medicament_id=?" +
                    " GROUP BY email ";


    public OrderDAO(ProxyConnection connection) {
        super(connection);
    }


    @Override
    public List<Order> selectAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<Order> selectEntityByKey(String key) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(String key) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws DAOException {
        return false;
    }


    //insert  order to DB
    @Override
    public String insert(Order order) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_BASKET)) {

            statement.setInt(1, order.getMedicamentID());
            statement.setDouble(2, order.getCount());
            statement.setInt(3, Integer.parseInt(selectUserIDByLogin(order.getUserLogin()).get()));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when insert order = " + order + " into db: ", e);
        }
        return order.getUserLogin();
    }

    //find user ID by login
    public Optional<String> selectUserIDByLogin(String login) throws DAOException {
        String userId = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getString("id");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select userID = " + userId + " from db: ", e);
        }
        return Optional.ofNullable(userId);
    }

    //delete order by order ID
    public boolean deleteOrderById(int orderID) throws DAOException {
        boolean confirm = false;
        String userId = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEDICAMENT_ORDER_BY_ID)) {
            statement.setInt(1, orderID);
            statement.executeUpdate();
            confirm = true;
        } catch (SQLException e) {
            throw new DAOException("Fail when delete order = " + orderID + " from db: ", e);
        }

        return confirm;
    }

    //delete order by login
    public void deleteOrdersByLohin(String login) throws DAOException {
        int userId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEDICAMENT_ORDERS_LOGIN)) {
            userId = Integer.parseInt(selectUserIDByLogin(login).get());
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when delete ALL orders by Login = " + login + " from db: ", e);
        }

    }

    //select medicament order by user ID

    public List<Order> selectMedicamentOrder(String userId) throws DAOException {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICAMENT_ORDER_BY_USER_ID)) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setMedicamentID(resultSet.getInt("medicament_id"));
                order.setCount(resultSet.getDouble("count"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select medicamentOrder where userID = " + userId + " from db: ", e);
        }
        return orderList;
    }

    //insert  user request  to DB

    public void insertRequest(int userId, int medID) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER_REQUEST)) {

            statement.setInt(1, userId);
            statement.setInt(2, medID);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when insert user request about medicament  = ", e);
        }
    }

    //select email   by medicament ID

    public List<String> selectRequestMedMail(int medicamentId) throws DAOException {
        List<String> mailList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EMAIL_FROM_REQUEST_BY_USER)) {
            statement.setInt(1, medicamentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                mailList.add(email);
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select email where medicamentId = " + medicamentId + " from db: ", e);
        }
        return mailList;
    }

    //delete user Request by medicamentID
    public void deleteUserReqByMedID(int medicamentId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_REQUEST_BY_MED_ID)) {
            statement.setInt(1, medicamentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when delete user Request by medicamentId = " + medicamentId + " from db: ", e);
        }

    }

}

