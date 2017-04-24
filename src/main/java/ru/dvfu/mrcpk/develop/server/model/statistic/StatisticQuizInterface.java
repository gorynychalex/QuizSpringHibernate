package ru.dvfu.mrcpk.develop.server.model.statistic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gorynych on 25.04.17.
 */
public interface StatisticQuizInterface extends Serializable {
    float getMark();
    void setMark(float mark);
    public List<StatisticQuestions> getStatisticQuestions();
}
