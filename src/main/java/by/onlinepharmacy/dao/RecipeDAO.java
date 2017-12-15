package by.onlinepharmacy.dao;


import by.onlinepharmacy.entity.Recipe;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class RecipeDAO extends AbstractDAO<String, Recipe> {


    private static final String SQL_SELECT_RECYPE_NUMBER_RECYPE_AND_MEDID =
            "SELECT id ,  date  FROM written_recipe  WHERE e_recipe = ? and medicament_id=?";
    private static final String SQL_SELECT_RECYPE_NUMBER_RECYPE =
            "SELECT id , medicament_id, date  FROM written_recipe  WHERE e_recipe = ?  ";


    public RecipeDAO(ProxyConnection connection) {
        super(connection);
    }


    @Override
    public List<Recipe> selectAll() throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public Optional<Recipe> selectEntityByKey(String key) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public boolean delete(String key) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public boolean update(Recipe entity) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    @Override
    public String insert(Recipe entity) throws DAOException {
        throw new UnsupportedOperationException("don't support this method");
    }

    //find recipe by number recipe ID and medicament ID
    public Optional<List<Recipe>> selectRecipeByUserId(String numberRecype, int medicamentId) throws DAOException {
        List<Recipe> recipeList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_RECYPE_NUMBER_RECYPE_AND_MEDID)) {
            statement.setString(1, numberRecype);
            statement.setInt(2, medicamentId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(resultSet.getString("id")));
                recipe.setMedicamentId(medicamentId);
                recipe.setValidityRecipe(Date.valueOf(resultSet.getString("date")));
                recipe.setNumberReceipt(numberRecype);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                java.util.Date currentTime = gregorianCalendar.getTime();

                if (currentTime.before(recipe.getValidityRecipe())) {
                    recipeList.add(recipe);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select recipe from db: ", e);
        }

        return Optional.ofNullable(recipeList);
    }


    //find recipe by number recipe ID
    public Optional<List<Recipe>> selectRecipeByUserId(String numberRecype) throws DAOException {
        List<Recipe> recipeList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_RECYPE_NUMBER_RECYPE)) {
            statement.setString(1, numberRecype);
            System.out.println(numberRecype);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(resultSet.getString("id")));
                recipe.setMedicamentId(Integer.parseInt(resultSet.getString("medicament_id")));
                recipe.setValidityRecipe(Date.valueOf(resultSet.getString("date")));
                recipe.setNumberReceipt(numberRecype);

                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                java.util.Date currentTime = gregorianCalendar.getTime();

                if (currentTime.before(recipe.getValidityRecipe())) {
                    recipe.setState(true);
                } else {
                    recipe.setState(false);
                }

                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select recipe from db: ", e);
        }

        return Optional.ofNullable(recipeList);
    }


}