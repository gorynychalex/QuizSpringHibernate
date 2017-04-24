package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;

/**
 * Created by gorynych on 20.04.17.
 */

@Repository
public class StatisticOptionDAO implements StatisticOptionsDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void addStatisticOption(int squestionId, StatisticOptions statisticOption) {
        currentSession().persist(statisticOption);
        StatisticQuestions currentStatisticQuestion = currentSession().get(StatisticQuestions.class, squestionId);
        currentStatisticQuestion.getStatisticOptionsList().add(statisticOption);
        currentSession().save(currentStatisticQuestion);
    }

    @Override
    public StatisticOptionsInterface getStatisticOptionById(int id) {
        return null;
    }
}
