package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.io.Serializable;
import java.util.List;

public interface QuizServiceInterface extends Serializable{
    QuizInterface getQuizById(Number id);
    QuizInterface getQuizByIdLazy(Number id);
    void addQuiz(QuizInterface quiz);
    List<QuizInterface> list();
    void removeById(Number id);
    List<Float> getResultByQuizId(Number id, Number sessionId);
}
