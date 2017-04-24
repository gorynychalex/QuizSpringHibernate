package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gorynych on 10.04.17.
 *
 * Interface STATISTIC of USER and QUIZ
 *
 * create SESSION-ID and set TIMESTAMP
 */
public interface StatisticUserQuizSessionsInterface extends Serializable{
    float getMark();
    void setMark(float mark);
    public List<StatisticQuestions> getStatisticQuestionsList();
}
