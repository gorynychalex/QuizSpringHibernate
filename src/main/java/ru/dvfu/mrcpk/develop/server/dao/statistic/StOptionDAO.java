package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.statistic.StOption;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuestion;

@Repository
public class StOptionDAO {


    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addStatisticOption(StQuestion stQuestion, Option option) {

        StOption stOption = new StOption(option);

        currentSession().save(stOption);

//        StatisticQuestions currentStatisticQuestion = currentSession().get(StQuestion.class, id);
        stQuestion.getStOptionList().add(stOption);
        currentSession().save(stQuestion);
    }


}
