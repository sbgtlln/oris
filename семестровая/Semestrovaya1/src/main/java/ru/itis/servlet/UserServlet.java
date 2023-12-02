package ru.itis.servlet;

import ru.itis.dao.UserRepository;
import ru.itis.dao.UserRepositoryJdbcImpl;
import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1415qweASD";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrovaya";

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            userRepository = new UserRepositoryJdbcImpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
