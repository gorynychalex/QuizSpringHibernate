package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;
import java.util.List;

public interface QuizInterface extends Serializable {

    Number getId();

    void setId(int id);

    String getName();

    void setName(String text);

    List<Question> getQuestions();

    void setQuestions(List<Question> question);

    int getQnums();

    void setQnums(int qnums);

    String getPicture();

    void setPicture(String picture);

    boolean isEnable();
}
