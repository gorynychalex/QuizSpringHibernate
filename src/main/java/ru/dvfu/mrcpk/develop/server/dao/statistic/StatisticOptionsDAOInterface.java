package ru.dvfu.mrcpk.develop.server.dao.statistic;

import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptionsInterface;

import java.io.Serializable;

/**
 * Created by gorynych on 12.04.17.
 */
public interface StatisticOptionsDAOInterface extends Serializable{

    void addStatisticOption(int squestionId, StatisticOptions statisticOption);

    StatisticOptionsInterface getStatisticOptionById(int id);
}
