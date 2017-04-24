package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticOptionsDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptionsInterface;

/**
 * Created by gorynych on 20.04.17.
 */
@Service
public class StatisticOptionService implements StatisticOptionServiceInterface{

    @Autowired
    private StatisticOptionsDAOInterface statisticOptionsDAO;

    @Transactional
    @Override
    public void addStatisticOption(int squestionId, StatisticOptions statisticOption) {
        statisticOptionsDAO.addStatisticOption(squestionId,statisticOption);
    }
}
