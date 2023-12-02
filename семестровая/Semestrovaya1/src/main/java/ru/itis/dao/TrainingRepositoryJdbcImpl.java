package ru.itis.dao;

import ru.itis.models.Training;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingRepositoryJdbcImpl implements TrainingRepository {
    private Connection connection;
    private Statement statement;
    public TrainingRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }


    @Override
    public Training getTrainingById(int trainingId) {
        try {
            String sql = "select * from trainings where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, trainingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Training.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainingList = new ArrayList<>();

        try {
            String sql = "select * from trainings";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Training training = Training.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainingList;
    }

    @Override
    public void addTraining(Training training) {
        try {
            String sql = "insert into trainings (title, description) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, training.getTitle());
            preparedStatement.setString(2, training.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTraining(Training training) {
        try {
            String sql = "update trainings set title = ?, description = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, training.getTitle());
            preparedStatement.setString(2, training.getDescription());
            preparedStatement.setLong(3, training.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteTraining(int trainingId) {
        try {
            String sql = "delete from trainings where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, trainingId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
