package by.onlinepharmacy.dao;

import by.onlinepharmacy.entity.HistoryBuying;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;


public class BuyingHistoryDAO extends AbstractDAO<String, HistoryBuying> {


    private static final String SQL_INSERT_IN_HISTORY =
            "INSERT INTO buy_history" + " (user_id,  medicament_id, date, count,name )" +
                    " VALUES (?, ?, ?, ?,? ) ";

    private static final String SQL_SELECT_ALL_INFORMATION_HISTORY =
            "SELECT  date, SUM(count) AS 'Count' ,name FROM buy_history GROUP BY name,date ORDER BY date DESC";

    private static final String SQL_SELECT_INFORMATION_HISTORY_ONE_USER =
            "SELECT  date, SUM(count) AS 'Count' ,name FROM buy_history WHERE user_id=? GROUP BY name,date ORDER BY date DESC";

    private static final String SQL_SELECT_COUNT_BUYING_TODAY =
            "SELECT SUM(count) AS count FROM buy_history WHERE date=CURDATE() ;";

    private static final String SQL_SELECT_BESTSALLER_TODAY =
            "SELECT medicament.name AS name   FROM online_pharmacy.buy_history AS history \n" +
                    "JOIN medicament_inf AS medicament\n" +
                    " ON medicament.id=history.medicament_id \n" +
                    " WHERE date=CURDATE() " +
                    " GROUP BY medicament.name  LIMIT 1;";


    public BuyingHistoryDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<HistoryBuying> selectAll() throws DAOException {
        List<HistoryBuying> historyBuyingList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_INFORMATION_HISTORY);
            while (resultSet.next()) {
                HistoryBuying historyBuying = new HistoryBuying();
                historyBuying.setDateBuying(resultSet.getDate("date"));
                historyBuying.setCountByuing(resultSet.getDouble("count"));
                historyBuying.setName(resultSet.getString("name"));
                historyBuyingList.add(historyBuying);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all medicament from db: ", e);
        }


        return historyBuyingList;
    }

    @Override
    public Optional<HistoryBuying> selectEntityByKey(String key) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public boolean delete(String key) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public boolean update(HistoryBuying entity) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public String insert(HistoryBuying historyBuying) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_IN_HISTORY)) {
            statement.setInt(1, historyBuying.getUserId());
            statement.setInt(2, historyBuying.getMedicamentId());
            GregorianCalendar calendar = new GregorianCalendar();
            java.util.Date utilStartDate = historyBuying.getDateBuying();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            statement.setDate(3, sqlStartDate);
            statement.setDouble(4, historyBuying.getCountByuing());
            statement.setString(5, historyBuying.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when insert into history information = " + historyBuying + " into db: ", e);
        }
        return historyBuying.getName();
    }

    //select history one user
    public List<HistoryBuying> selectAllHistoryUser(int userID) throws DAOException {
        List<HistoryBuying> historyBuyingList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_INFORMATION_HISTORY_ONE_USER)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HistoryBuying historyBuying = new HistoryBuying();
                historyBuying.setDateBuying(resultSet.getDate("date"));
                historyBuying.setCountByuing(resultSet.getDouble("count"));
                historyBuying.setName(resultSet.getString("name"));
                historyBuyingList.add(historyBuying);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all medicament from db: ", e);
        }


        return historyBuyingList;
    }


    //select count buying today
    public int selectBuyingCountToday() throws DAOException {
        int count = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_BUYING_TODAY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = (resultSet.getInt("count"));

            }

        } catch (SQLException e) {
            throw new DAOException("Fail when count buying today  from db: ", e);
        }
        return count;
    }

    //select count buying today
    public String selectTopPreparatToday() throws DAOException {
        String bestMedicament = "-";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BESTSALLER_TODAY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bestMedicament = (resultSet.getString("name"));

            }

        } catch (SQLException e) {
            throw new DAOException("Fail when count buying today  from db: ", e);
        }
        return bestMedicament;
    }


}

