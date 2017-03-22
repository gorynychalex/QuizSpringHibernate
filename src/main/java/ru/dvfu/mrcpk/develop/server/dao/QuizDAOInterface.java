package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.util.List;

public interface QuizDAOInterface {
    QuizInterface getQuizById(Number id);
    QuizInterface getQuizByIdLazy(Number id);
    void addQuiz(QuizInterface quiz);
    List<QuizInterface> list();
    void remove(Number id);
}
