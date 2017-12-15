package by.onlinepharmacy.dao;

import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.DAOException;
import by.onlinepharmacy.pool.ProxyConnection;
import by.onlinepharmacy.util.GenderType1;
import by.onlinepharmacy.util.date.GenderType;
import by.onlinepharmacy.util.date.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAO extends AbstractDAO<String, User> {


    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, login,  email, sex, name, surname, birth_date, electrone_recipe, role,  city " +
                    " FROM user  ";

    private static final String SQL_SELECT_USERS_BY_PART_LOGIN =
            "SELECT id, login, password,  email, sex, name, surname, birth_date, electrone_recipe, role,  city " +
                    " FROM user WHERE login LIKE ? ";

    private static final String SQL_SELECT_USERS_BY_LOGIN =
            "SELECT   id,login, password,  email, sex, name, surname, birth_date, electrone_recipe, role,  city " +
                    " FROM user WHERE login= ?  ";


    private static final String SQL_SELECT_USER =
            "SELECT id, login,  email, sex, name, surname, birth_date, electrone_recipe, role,  city  " +
                    "FROM user  WHERE login = ?";

    private static final String SQL_SELECT_EMAIL_BY_ITSELF =
            "SELECT email FROM user WHERE email = ?";


    private static final String SQL_SELECT_PASSWORD =
            "SELECT password FROM user WHERE login = ?";

    private static final String SQL_SELECT_ROLE =
            "SELECT role FROM user WHERE login = ?";

    private static final String SQL_SELECT_USER_ID =
            "SELECT id FROM user WHERE login = ?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO user" + " (login, password,  email, sex, name, surname, birth_date, electrone_recipe, role, city)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

    private static final String SQL_UPDATE_USER =
            "UPDATE user SET sex = ?, name = ?, surname = ?,    birth_date = ?, city = ?, electrone_recipe= ? " +
                    " WHERE login = ? ";

    private static final String SQL_UPDATE_USER_PASSWORD =
            "UPDATE user SET   password=?  " +
                    " WHERE login = ? ";

    private static final String SQL_DELETE_USER =
            "DELETE FROM user WHERE id = ?";

    private static final String SQL_SELECT_NUMBER_RECIPE =
            "SELECT electrone_recipe FROM user WHERE login = ?";

    private static final String SQL_SELECT_LOGIN =
            "SELECT login FROM user WHERE login = ?";

    private static final String SQL_SELECT_RECIPE =
            "SELECT electrone_recipe FROM user WHERE electrone_recipe = ?";

    private static final String SQL_SELECT_ALL_USER =
            "SELECT count(*) AS count FROM user ";


    public UserDAO(ProxyConnection connection) {
        super(connection);
    }

    //find  all users
    @Override
    public List<User> selectAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(GenderType1.valueOf(resultSet.getString("sex").toUpperCase()));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setNumberReceipt(resultSet.getString("electrone_recipe"));
                user.setRole(UserType.valueOf(resultSet.getString("role").toUpperCase()));
                user.setCity(resultSet.getString("city"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all users from db: ", e);
        }

        return userList;
    }

    //find  resipe number in db  by login
    public Optional<String> selectRecipeNumberbyLogin(String login) throws DAOException {
        String numberRecipe = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_NUMBER_RECIPE)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberRecipe = resultSet.getString("electrone_recipe");

            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select number recipe by login = " + login + " from db: ", e);
        }
        return Optional.ofNullable(numberRecipe);
    }

    //find  user in db  by user login
    @Override
    public Optional<User> selectEntityByKey(String key) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER)) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(GenderType1.valueOf(resultSet.getString("sex").toUpperCase()));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setRole(UserType.valueOf(resultSet.getString("role").toUpperCase()));
                user.setCity(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select user by key = " + key + " from db: ", e);
        }
        return Optional.ofNullable(user);

    }

    //delete  user in db  by user ID
    @Override
    public boolean delete(String key) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setString(1, key);
            success = (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DAOException("Fail when delete user = " + key + " from db: ", e);
        }
        return success;
    }

    //update  user in db
    @Override
    public boolean update(User user) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getGender().toString());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setDate(4, user.getBirthDate());
            statement.setString(5, user.getCity());
            statement.setString(6, user.getNumberReceipt());
            statement.setString(7, user.getLogin());

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Fail when update user = " + user + " in db: ", e);
        }
        return success;

    }

    //update  user password in db
    public boolean updatePassword(String login, String cryptoPassword) throws DAOException {
        boolean success;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
            statement.setString(1, cryptoPassword);
            statement.setString(2, login);
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Fail when update user pass = " + login + " in db: ", e);
        }
        return success;

    }


    //insert  user to db
    @Override
    public String insert(User user) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getGender().toString());
            statement.setString(5, user.getName());
            statement.setString(6, user.getSurname());
            statement.setDate(7, user.getBirthDate());
            statement.setString(8, user.getNumberReceipt());
            statement.setString(9, user.getRole().toString());
            statement.setString(10, user.getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail when insert user = " + user + " into db: ", e);
        }
        return user.getLogin();
    }

    //find  password in db by password
    public Optional<String> selectPassword(String login) throws DAOException {
        String password = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PASSWORD)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select password by login = " + login + " from db: ", e);
        }
        return Optional.ofNullable(password);
    }

    //find  login in db by login
    public Optional<String> selectLogin(String login) throws DAOException {
        String existLogin = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                existLogin = resultSet.getString("login");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select login = " + login + " from db: ", e);
        }
        return Optional.ofNullable(existLogin);
    }

    //find  recipe in db
    public Optional<String> selectRecipe(String recipe) throws DAOException {
        String existLogin = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_RECIPE)) {
            statement.setString(1, recipe);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                existLogin = resultSet.getString("electrone_recipe");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select login = " + recipe + " from db: ", e);
        }
        return Optional.ofNullable(existLogin);
    }

    //find  email in db by email
    public Optional<String> selectEmailByItself(String email) throws DAOException {
        String currEmail = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EMAIL_BY_ITSELF)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                currEmail = resultSet.getString("email");
            }
        } catch (SQLException e) {
            throw new DAOException("Fail when select email = " + email + " from db: ", e);
        }
        return Optional.ofNullable(currEmail);
    }

    //find  all users by part login
    public List<User> selectUserByLogin(String login) throws DAOException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_PART_LOGIN)) {
            statement.setString(1, login + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(GenderType1.valueOf(resultSet.getString("sex").toUpperCase()));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setNumberReceipt(resultSet.getString("electrone_recipe"));
                user.setRole(UserType.valueOf(resultSet.getString("role").toUpperCase()));
                user.setCity(resultSet.getString("city"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select all users from db: ", e);
        }

        return userList;
    }

    //find user by login (one user)
    public User selectUserByLoginCut(String login) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(GenderType1.valueOf(resultSet.getString("sex").toUpperCase()));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setNumberReceipt(resultSet.getString("electrone_recipe"));
                user.setRole(UserType.valueOf(resultSet.getString("role").toUpperCase()));
                user.setCity(resultSet.getString("city"));
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select  user by login  from db: ", e);
        }

        return user;
    }

    //find user role
    public Optional<UserType> selectRoleByLogin(String login) throws DAOException {
        UserType role = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ROLE)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = UserType.valueOf(resultSet.getString("role").toUpperCase());
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select role from db: ", e);
        }
        return Optional.ofNullable(role);
    }

    //find user id by login
    public int selectIdByLogin(String login) throws DAOException {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select role from db: ", e);
        }
        return id;
    }

    //select  all register user
    public int selectAllUser() throws DAOException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USER)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("Fail when select role from db: ", e);
        }
        return count;
    }

}



