package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import java.util.List;

public interface QuestionServiceInterface {
    QuestionInterface getById(Number id);
    List<QuestionInterface> list(Number quizid);
    void add(QuestionInterface question);
    void update(QuestionInterface question);
    void remove(Number id);
}
