package by.onlinepharmacy.dao;

import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.entity.Recipe;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MedicamentDAO extends AbstractDAO<String, Medicament> {

    private static final String SQL_SELECT_ALL_MEDICAMENTS =
            "SELECT id, name,  dosage, count, country , price, recipe, imagePath " +
                    " FROM medicament_inf ";

    private static final String SQL_SELECT_MEDICAMENTS_BY_NAME_DOSAGE =
            "SELECT id, name,  dosage, count, country , price, recipe " +
                    " FROM medicament_inf WHERE name=? and dosage=? ";

    private static final String SQL_SELECT_SECOND_INFORMATION_MEDICAMENT =
            "SELECT name,  dosage, country , price  FROM  medicament_inf WHERE id=? ";

    private static final String SQL_SELECT_SECOND_INFORMATION_MEDICAMENT_TO_RECIPE =
            "SELECT name,  dosage   FROM  medicament_inf WHERE id=? ";


    private static final String SQL_SELECT_MEDICAMENT =
            "SELECT id, name,  dosage, count, country , price, recipe  " +
                    "FROM medicament_inf  WHERE login = ?";

    private static final String SQL_SELECT_MEDICAMENT_BY_ID =
            "SELECT   name,  dosage, count, country , price, recipe  " +
                    "FROM medicament_inf  WHERE id = ?";

    private static final String SQL_SELECT_COUNT_BY_ID =
            "SELECT count FROM medicament_inf  WHERE id = ?";

    private static final String SQL_DELETE_MEDICAMENT =
            "DELETE FROM medicament_inf WHERE id = ?";

    private static final String SQL_UPDATE_COUNT_MEDICAMENT =
            "UPDATE medicament_inf SET  count = ?  WHERE id = ?";

    private static final String SQL_UPDATE_MEDICAMENT =
            "UPDATE medicament_inf SET name = ?, dosage = ?, count = ?,   country = ?, price = ?, recipe = ?   " +
                    " WHERE id = ?";

    private static final String SQL_UPDATE_MEDICAMENT_FOTO =
            "UPDATE medicament_inf SET  imagePath=?  " +
                    " WHERE id = ?";


    private static final String SQL_INSERT_MEDICAMENT =
            "INSERT INTO medicament_inf" + " (name,  dosage, count, country , price, recipe, imagePath )" +
                    " VALUES (?, ?, ?, ?, ?, ?,?   ) ";


    private static final String SQL_SELECT_MEDICAMENTS_BY_NAME =
            "SELECT id, name,  dosage, count, country , price, recipe, imagePath " +
                    " FROM medicament_inf WHERE name LIKE   ? ";

    private static final String SQL_SELECT_NAME_BY_ID =
            "SELECT  name  FROM medicament_inf WHERE id=? ";


    public MedicamentDAO(ProxyConnection connection) {
        super(connection);
    }

    //select all medicament from DB
    @Override
    public List<Medicament> selectAll() throws DAOException {
        List<Medicament> medicamentList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MEDICAMENTS);
            while (resultSet.next()) {
                Medicament medicament = new Medicament();
                medicament.setId(Integer.parseInt(resultSet.getString("id")));
                medicament.setName(resultSet.getString("name"));
                medicament.setDosage(resultSet.getString("dosage"));
                medicament.setAmountInStock(resultSet.getInt("count"));
                medicament.setCountry(resultSet.getString("country"));
                medicament.setPrice(resultSet.getDouble("price"));
                medicament.setRecipeRequired(resultSet.getBoolean("recipe"));
                medicament.setImagePath(resultSet.getString("imagePath"));

                medicamentList.add(medicament);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all medicament from db: ", e);
        }

        return medicamentList;
    }

    //select medicament by key
    @Override
    public Optional<Medicament> selectEntityByKey(String key) throws DAOException {
        Medicament medicament = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICAMENT)) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                medicament = new Medicament();
                medicament.setId(Integer.parseInt(resultSet.getString("id")));
                medicament.setName(resultSet.getString("name"));
                medicament.setDosage(resultSet.getString("dosage"));
                medicament.setAmountInStock(resultSet.getInt("count"));
                medicament.setCountry(resultSet.getString(resultSet.getString("country")));
                medicament.setPrice(resultSet.getDouble(resultSet.getString("price")));

                String isRecipe = resultSet.getBoolean("recipe") ? "true" : "false";
                medicament.setRecipeRequired(Boolean.parseBoolean(isRecipe));

            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select medicament by key = " + key + " from db: ", e);
        }
        return Optional.ofNullable(medicament);

    }

    //delete medicament by key

    @Override
    public boolean delete(String key) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEDICAMENT)) {
            statement.setString(1, key);
            success = (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DAOException("Fail when delete medicament = " + key + " from db: ", e);
        }
        return success;
    }


    //update medicament by id

    @Override
    public boolean update(Medicament medicament) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MEDICAMENT)) {
            statement.setString(1, medicament.getName());
            statement.setString(2, medicament.getDosage());
            statement.setDouble(3, medicament.getAmountInStock());
            statement.setString(4, medicament.getCountry());
            statement.setDouble(5, medicament.getPrice());
            String isRecipe = medicament.isRecipeRequired() ? "1" : "0";
            statement.setString(6, isRecipe);
            statement.setDouble(7, medicament.getId());

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Fail when update medicament = " + medicament + " in db: ", e);
        }
        return success;
    }

    //insert medicament into DB

    @Override
    public String insert(Medicament medicament) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MEDICAMENT)) {

            statement.setString(1, medicament.getName());
            statement.setString(2, medicament.getDosage());
            statement.setDouble(3, medicament.getAmountInStock());
            statement.setString(4, medicament.getCountry());
            statement.setDouble(5, medicament.getPrice());
            String isRecipe = medicament.isRecipeRequired() ? "1" : "0";
            statement.setString(6, isRecipe);
            statement.setString(7, medicament.getImagePath());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when insert medicament = " + medicament + " into db: ", e);
        }
        return medicament.getName();
    }


    //find medicament by part name

    public List<Medicament> findMedicamentByName(String name) throws DAOException {
        List<Medicament> medicamentList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICAMENTS_BY_NAME)) {
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Medicament medicament = new Medicament();
                medicament.setId(Integer.parseInt(resultSet.getString("id")));
                medicament.setName(resultSet.getString("name"));
                medicament.setDosage(resultSet.getString("dosage"));
                medicament.setAmountInStock(resultSet.getInt("count"));
                medicament.setCountry(resultSet.getString("country"));
                medicament.setPrice(resultSet.getDouble("price"));

                medicament.setRecipeRequired(resultSet.getBoolean("recipe"));
                medicament.setImagePath(resultSet.getString("imagePath"));

                medicamentList.add(medicament);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when  find medicament from db: ", e);
        }
        return medicamentList;
    }


    //Add  medicament information to Order

    public void addInformationToOrder(Order order, int medicamentID) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SECOND_INFORMATION_MEDICAMENT)) {
            statement.setInt(1, medicamentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order.setName(resultSet.getString("name"));
                order.setDosage(resultSet.getString("dosage"));
                order.setCountry(resultSet.getString("country"));
                order.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when add medicament information to Order: ", e);
        }
    }


    //Add  medicament information to Recipe

    public void addInformationToRecipe(Recipe recipe, int medicamentID) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SECOND_INFORMATION_MEDICAMENT_TO_RECIPE)) {
            statement.setInt(1, medicamentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                recipe.setName(resultSet.getString("name"));
                recipe.setDosage(resultSet.getString("dosage"));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when add Recipe information to Recipe: ", e);
        }
    }


    //Update medicament count after Order


    public boolean updateCount(int medicamentID, double resultCount) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COUNT_MEDICAMENT)) {
            statement.setDouble(1, resultCount);
            statement.setInt(2, medicamentID);
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Fail when update count medicament = " + resultCount + " in db: ", e);
        }
        return success;
    }

    //find count medicament by id befor  Order
    public double selectOldCountMedicament(int medicamentID) throws DAOException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COUNT_BY_ID)) {
            statement.setInt(1, medicamentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = (resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when find count medicament  in db: ", e);

        }
        return count;
    }

    //find name medicament by id
    public String selectNamebyID(int id) throws DAOException {
        String name = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_NAME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when find name medicament by id  in db: ", e);

        }
        return name;
    }

    public boolean checkUniqePreparat(String name, String dosage) throws DAOException {
        boolean result = false;
        List<Medicament> medicamentList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICAMENTS_BY_NAME_DOSAGE)) {
            statement.setString(1, name);
            statement.setString(2, dosage);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all medicament from db: ", e);
        }

        return result;
    }

    //update foto medicament in dB
    public boolean updateMedicamentFoto(Medicament medicament) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MEDICAMENT_FOTO)) {

            statement.setString(1, medicament.getImagePath());
            statement.setDouble(2, medicament.getId());

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Fail when update medicament Foto = " + medicament + " in db: ", e);
        }
        return success;
    }

    //select medicament by id

    public Optional<Medicament> selectEntityById(int id) throws DAOException {
        Medicament medicament = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MEDICAMENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                medicament = new Medicament();
                medicament.setName(resultSet.getString("name"));
                medicament.setDosage(resultSet.getString("dosage"));
                medicament.setAmountInStock(resultSet.getInt("count"));
                medicament.setCountry(resultSet.getString("country"));

                medicament.setPrice(resultSet.getDouble("price"));
                String isRecipe = resultSet.getBoolean("recipe") ? "true" : "false";
                medicament.setRecipeRequired(Boolean.parseBoolean(isRecipe));

            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select medicament by id = " + id + " from db: ", e);
        }
        return Optional.ofNullable(medicament);

    }

}