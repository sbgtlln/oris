package servlets;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_INSERT_INTO_USERS = "insert into driver(login,password,first_name,last_name) values ";

    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }
    @Override
    public List findAllByAge() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("last_name"))
                        .login(resultSet.getString("login"))
                        .build();
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String sql = SQL_INSERT_INTO_USERS + "('" + entity.getLogin() + "', '" + entity.getPassword() + "', '" + entity.getFirstName() + "', '" + entity.getSecondName()  + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        try {
            String username = login.getLogin();
            String password = login.getPassword();

            String sql = "SELECT * FROM driver WHERE login = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
