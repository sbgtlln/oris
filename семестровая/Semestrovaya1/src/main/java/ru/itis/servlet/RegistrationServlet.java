package ru.itis.servlet;

import ru.itis.dao.UserRepository;
import ru.itis.dao.UserRepositoryJdbcImpl;
import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import org.postgresql.util.PSQLException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
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
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User newUser = User.builder()
                .username(username)
                .password(password)
                .build();

        userRepository.addUser(newUser);
        response.sendRedirect("login");
    }
}
