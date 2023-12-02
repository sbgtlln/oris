package ru.itis.dao;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {
    private Connection connection;
    private Statement statement;
    public UserRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }
    public UserRepositoryJdbcImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public User getUserById(Long userId) {
        try {
            String sql = "select * from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String sql = "select * from users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "insert into users (username, password) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            String sql = "update users set username = ?, password = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            String sql = "delete from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
