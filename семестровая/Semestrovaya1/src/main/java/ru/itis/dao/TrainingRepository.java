package ru.itis.dao;

import ru.itis.models.Training;

import java.util.List;

public interface TrainingRepository {
    Training getTrainingById(int trainingId);
    List<Training> getAllTrainings();
    void addTraining(Training training);
    void updateTraining(Training training);
    void deleteTraining(int trainingId);
}
