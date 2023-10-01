package java_oop_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImp implements UserRepository {
    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from hw2";

    public UserRepositoryJdbcImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        try {
            String sql = "SELECT * FROM hw2 WHERE age = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, age);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("login")
                );
                result.add(user);
            }

            if (result.isEmpty()) {
                System.out.println("По введенному возрасту ничего не найдено...");
            } else {
                for (User user : result) {
                    System.out.println(user.getFirstName() + " " + user.getLastName());
                }
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при поиске пользователей по возрасту", e);
        }
    }


    public List<User> findByLogin(String login) {
        try {
            String sql = "SELECT * FROM hw2 WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("login")
                );
                result.add(user);
            }

            if (result.isEmpty()) {
                System.out.println("По введенному логину ничего не найдено...");
            } else {
                for (User user : result) {
                    System.out.println(user.getFirstName() + " " + user.getLastName());
                }
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при поиске пользователя по логину", e);
        }
    }

}
