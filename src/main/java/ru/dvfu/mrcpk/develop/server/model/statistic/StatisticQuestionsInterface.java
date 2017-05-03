package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;

import java.io.Serializable;

/**
 * Created by gorynych on 10.04.17.
 *
 * Interface Statistic of Questions
 */
public interface StatisticQuestionsInterface extends Serializable{
    int getId();
    Question getQuestion();
    float getMark();
    void setQuestion(Question question);
    void setMark(float mark);
    Float getResult();
}
