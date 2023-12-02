package ru.itis.servlet;

import ru.itis.dao.TrainingRepository;
import ru.itis.dao.TrainingRepositoryJdbcImpl;
import ru.itis.dao.UserRepository;
import ru.itis.dao.UserRepositoryJdbcImpl;
import ru.itis.models.Training;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TrainingServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1415qweASD";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrovaya";
    private TrainingRepository trainingRepository;

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
            trainingRepository = new TrainingRepositoryJdbcImpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Training> trainings = trainingRepository.getAllTrainings();
        request.setAttribute("trainings", trainings);
        request.getRequestDispatcher("/jsp/training.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Training newTraining = Training.builder()
                .title(title)
                .description(description)
                .build();

        trainingRepository.addTraining(newTraining);

        List<Training> updatedTrainings = trainingRepository.getAllTrainings();
        request.setAttribute("trainings", updatedTrainings);

        request.getRequestDispatcher("/jsp/training.jsp").forward(request, response);
    }
}
