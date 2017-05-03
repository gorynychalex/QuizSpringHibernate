package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.User;

import java.io.Serializable;
import java.util.List;

public interface QuizServiceInterface extends Serializable{
    QuizInterface getById(Number id);
    QuizInterface getByIdLazy(Number id);
    void addQuiz(QuizInterface quiz);
    List<QuizInterface> list();
    void removeById(Number id);
    List<Float> getResultByQuizId(Number quizId, Number sessionId);
    void addStatistic(Number id, int sessionId, User user);
}
