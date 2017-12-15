package by.onlinepharmacy.logic;

import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;

import java.util.List;

/**
 * Created by User on 23.07.2017.
 */
public interface ICommonLogic {
    List<Medicament> selectAllMedicament() throws BusinessLogicException;

    List<Medicament> findMedicament(String name) throws BusinessLogicException;

    User findUser(String login) throws BusinessLogicException;
}
