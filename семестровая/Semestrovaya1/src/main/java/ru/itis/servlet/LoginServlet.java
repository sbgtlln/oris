package ru.itis.servlet;

import ru.itis.dao.UserRepository;
import ru.itis.dao.UserRepositoryJdbcImpl;
import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
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
            userRepository = new UserRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       if (isValidLogin(username, password)) {
           request.getSession().setAttribute("user", userRepository.getUserByUsernameAndPassword(username, password));
           response.sendRedirect("main");
       } else {
           request.setAttribute("error", "Неверное имя пользователя или пароль");
           request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
       }
    }

    private boolean isValidLogin(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        return user != null;
    }
}
