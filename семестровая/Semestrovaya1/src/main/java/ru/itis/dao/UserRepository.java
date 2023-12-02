package ru.itis.dao;

import ru.itis.models.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository {
    User getUserById(Long userId);
    User getUserByUsernameAndPassword(String username, String password);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);
    Connection getConnection();
}
