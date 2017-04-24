package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;
import java.util.List;

public interface QuizInterface extends Serializable {

    String getName();

    void setName(String text);

    Number getId();

    void setId(int id);

    List<Question> getQuestions();

    void setQuestions(List<Question> question);

    int getQnums();

    void setQnums(int qnums);

}
