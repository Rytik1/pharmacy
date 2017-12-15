package by.onlinepharmacy.logic;

import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;

import java.util.List;


public interface IAdministratorLogic {
    void addNewMedicament(String name, String dosage, double amountInStock, String country,
                          double price, boolean recipeRequired ,String imagePath) throws BusinessLogicException;

    Medicament updateMedicament(int id, String name, String dosage, double amountInStock, String country,
                                double price, boolean recipeRequired ) throws BusinessLogicException;

    void deleteMedicament(String id) throws BusinessLogicException;

    void deleteUser(String id) throws BusinessLogicException;

    List<User> selectAllUser() throws BusinessLogicException;

    List<User> findUser(String login) throws BusinessLogicException;
}
