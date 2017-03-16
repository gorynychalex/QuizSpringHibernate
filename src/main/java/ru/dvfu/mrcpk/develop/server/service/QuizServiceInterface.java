package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.io.Serializable;
import java.util.List;

public interface QuizServiceInterface extends Serializable{
    QuizInterface getQuizById(Number id);
    void addQuiz(QuizInterface quiz);
    List<QuizInterface> list();
    void removeById(Number id);
}
