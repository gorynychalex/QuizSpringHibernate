package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.stereotype.Service;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptionsInterface;

/**
 * Created by gorynych on 20.04.17.
 */

public interface StatisticOptionServiceInterface {
    public void addStatisticOption(int squestionId, StatisticOptions statisticOption);
}
