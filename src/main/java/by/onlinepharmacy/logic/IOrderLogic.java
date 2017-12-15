package by.onlinepharmacy.logic;

import by.onlinepharmacy.entity.Order;
import by.onlinepharmacy.entity.Recipe;
import by.onlinepharmacy.exception.BusinessLogicException;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 23.07.2017.
 */
public interface IOrderLogic {
    void addNewOrder(int medicamentID, double count, String userLogin, double countResult) throws BusinessLogicException;

    List<Order> selectOrder(String login) throws BusinessLogicException;

    List<Order> deleteOrder(int orderId, double count, String login, int medicamentID) throws BusinessLogicException;

    void deleteALLOrderByLogin(String login) throws BusinessLogicException;

     Optional<List<Recipe>> checkRecipe(String login, int medcamentId) throws BusinessLogicException;
}
