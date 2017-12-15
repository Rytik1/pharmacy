package by.onlinepharmacy.logic.ImplLogic;

 import by.onlinepharmacy.dao.BuyingHistoryDAO;
 import by.onlinepharmacy.dao.MedicamentDAO;
import by.onlinepharmacy.dao.UserDAO;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.ConnectionPoolException;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.logic.IAdministratorLogic;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.pool.ProxyConnection;

 import java.util.ArrayList;
 import java.util.Comparator;
import java.util.List;


public class AdministratorLogic implements IAdministratorLogic {

    private static final String PRICE_REGEX = "^[0-9]{1,7}[.]?[0-9]{2}";
    private static final String COUNT_RECIPE_REGEX = "^[0-9]{1,7}[.]?[0-9]{0,3}";
    private static final String COUNTRY_REGEX = "[a-zA-Zа-яА-Я- ]{1,20}";

    //check price to REGEX
    public boolean isPriceValid(final String price) {
        return (price != null) && price.matches(PRICE_REGEX);
    }

    //check count to REGEX
    public boolean isCountValid(final String count) {
        return (count != null) && count.matches(COUNT_RECIPE_REGEX);
    }

    //check country to REGEX
    public boolean isCountryValid(final String country) {
        return (country != null) && country.matches(COUNTRY_REGEX);
    }

    //add new medicament to db
    @Override
    public void addNewMedicament(String name, String dosage, double amountInStock, String country,
                                 double price, boolean recipeRequired, String imagePath ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                Medicament medicament = new Medicament();
                medicament.setName(name.toUpperCase());
                medicament.setDosage(dosage);
                medicament.setAmountInStock(amountInStock);
                medicament.setCountry(country);
                medicament.setPrice(price);
                medicament.setRecipeRequired(recipeRequired);
                medicament.setImagePath(imagePath);
                 medicamentDAO.insert(medicament);
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when add new medicament: ", e);
        }

    }

    //update  medicament in db

    @Override
    public Medicament updateMedicament(int id, String name, String dosage, double amountInStock, String country,
                                 double price, boolean recipeRequired ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Medicament medicament=null;
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                medicament = new Medicament();
                medicament.setId(id);
                medicament.setName(name.toUpperCase());
                medicament.setDosage(dosage);
                medicament.setAmountInStock(amountInStock);
                medicament.setCountry(country);
                medicament.setPrice(price);
                System.out.println(medicament.getPrice());
                medicament.setRecipeRequired(recipeRequired);
                medicamentDAO.update(medicament);


        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }
      return medicament;
    }

    //delete  medicament in db by medicament id
    @Override
    public void deleteMedicament(String id) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                medicamentDAO.delete(id);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }

    }

    //delete  user in db by user id
    @Override
    public void deleteUser(String id) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                UserDAO userDAO = new UserDAO(connection);
                userDAO.delete(id);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }
    }

    //select all user from db
    @Override
    public List<User> selectAllUser() throws BusinessLogicException {
        List<User> userList;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            userList = userDAO.selectAll();
            if (userList != null) {
                userList.sort(Comparator.comparing(User::getName));
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return userList;
    }

    //find all user in db by user Login
    @Override
    public List<User> findUser(String login) throws BusinessLogicException {
        List<User> usertList;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            usertList = userDAO.selectUserByLogin(login);
            if (usertList != null) {
                usertList.sort(Comparator.comparing(User::getLogin));
            }

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check existence of login: ", e);
        }
        return usertList;
    }

    //check name and dosage with DB (find the same preparate)

    public boolean checkUniquePeparat(String name, String dosage) throws BusinessLogicException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try (ProxyConnection connection = pool.getConnection()) {
            MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
            result = medicamentDAO.checkUniqePreparat(name, dosage);

        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when check the user's existence: ", e);
        }
        return result;
    }

    //update medicament foto
    public void updateMedicamentFoto(int id , String imagePath) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);
                Medicament medicament = new Medicament();
                medicament.setId(id);

                medicament.setImagePath(imagePath);
                medicamentDAO.updateMedicamentFoto(medicament);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }

    }
    //get medicament by ID
    public Medicament getMedicamentByID(int id ) throws BusinessLogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Medicament medicament=null;
        try {
            try (ProxyConnection connection = pool.getConnection()) {
                MedicamentDAO medicamentDAO = new MedicamentDAO(connection);

                 medicament=medicamentDAO.selectEntityById(id).get();
                medicament.setId(id);
            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when update new medicament: ", e);
        }
      return medicament;
    }


    //get statistic for administrator (count registration user, bestsaler medicament in a day, count buying in a  day)
    public  List<String> getStatistic( ) throws BusinessLogicException {
        List<String> list=new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();

         try {
            try (ProxyConnection connection = pool.getConnection()) {
                BuyingHistoryDAO historyDAO=new BuyingHistoryDAO(connection);
                UserDAO userDAO=new UserDAO(connection);
                String countBuying= String.valueOf(historyDAO.selectBuyingCountToday());
                String bestMedicament=historyDAO.selectTopPreparatToday();
                String countRegistrUser= String.valueOf(userDAO.selectAllUser());
                list.add(countBuying);
                list.add(bestMedicament);
                list.add(countRegistrUser);

            }
        } catch (ConnectionPoolException | DAOException e) {
            throw new BusinessLogicException("Problem when get Statstic: ", e);
        }
        return list;
    }



}
