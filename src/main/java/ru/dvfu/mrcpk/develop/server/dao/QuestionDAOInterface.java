package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAOInterface {
    QuestionInterface getById(Number id);
    List<QuestionInterface> list();
    List<Question> list(Number quizid);
    void add(QuestionInterface question);
    void update(QuestionInterface question);
    void remove(Number id);
}
