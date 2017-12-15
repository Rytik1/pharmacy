package by.onlinepharmacy.logic.ImplLogic;

import by.onlinepharmacy.dao.BuyingHistoryDAO;
import by.onlinepharmacy.dao.MedicamentDAO;
import by.onlinepharmacy.dao.RecipeDAO;
import by.onlinepharmacy.dao.UserDAO;
import by.onlinepharmacy.entity.*;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.logic.ICommonLogic;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;

import java.util.*;

public class CommonLogic implements ICommonLogic {
    private static final String MEDICAMENT_REGEX = "[_A-Za-zа-яА-Я0-9- ]{1,20}";

    //check medicament name to REGEX
    public boolean isMedicamentNameValid(String name) {
        return (name != null) && name.matches(MEDICAMENT_REGEX);
    }

    //select all medicament from db
    @Override
    public List<Medicament> selectAllMedicament() throws BusinessLogicException {
        List<Medicament> medicamentList;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            medicamentList = medicamentDAO.selectAll();
            if (medicamentList != null) {
                medicamentList.sort(Comparator.comparing(Medicament::getName));
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem whith select all medicament: ", e);
        }
        return medicamentList;
    }

    //select  medicament from db by name
    @Override
    public List<Medicament> findMedicament(String name) throws BusinessLogicException {
        List<Medicament> medicamentList;

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            medicamentList = medicamentDAO.findMedicamentByName(name);
            if (medicamentList != null) {
                medicamentList.sort(Comparator.comparing(Medicament::getName));
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when find medicament by name: ", e);
        }
        return medicamentList;
    }

    //select  recipe from db by login
    public List<Recipe> findRecipe(String login) throws BusinessLogicException {
        List<Recipe> recipeList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            RecipeDAO recipeDAO = new RecipeDAO(connection);
            UserDAO userDAO = new UserDAO(connection);
            Optional<String> numberRecipe = userDAO.selectRecipeNumberbyLogin(login);

            if (numberRecipe.isPresent()) {
                recipeList = recipeDAO.selectRecipeByUserId(numberRecipe.get()).get();

                if (recipeList.size() != 0) {
                    for (Recipe recipe : recipeList) {
                        int medicamentId = recipe.getMedicamentId();
                        medicamentDAO.addInformationToRecipe(recipe, medicamentId);
                    }
                }
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem with find recipe: ", e);
        }
        return recipeList;
    }


    //find user by login (full login)
    @Override
    public User findUser(String login) throws BusinessLogicException {
        User user;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.selectUserByLoginCut(login);
            if (user == null) {
                throw new BusinessLogicException("Problem with find user: ");
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem with find user: ", e);
        }
        return user;
    }

    //get all history list
    public List<HistoryBuying> getListHistory() throws BusinessLogicException {
        List<HistoryBuying> historyBuyingList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            BuyingHistoryDAO buyingHistoryDAO = new BuyingHistoryDAO(connection);
            historyBuyingList = buyingHistoryDAO.selectAll();

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem with listHistory: ", e);
        }
        return historyBuyingList;
    }

    //get  history one of users list
    public List<HistoryBuying> getListHistory(String login) throws BusinessLogicException {
        List<HistoryBuying> historyBuyingList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            int userId = userDAO.selectUserByLoginCut(login).getId();
            System.out.println(userId + "!!!!!!!!!!!!!!!!!!!");

            BuyingHistoryDAO buyingHistoryDAO = new BuyingHistoryDAO(connection);
            historyBuyingList = buyingHistoryDAO.selectAllHistoryUser(userId);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem with listHistory: ", e);
        }
        return historyBuyingList;
    }

}
