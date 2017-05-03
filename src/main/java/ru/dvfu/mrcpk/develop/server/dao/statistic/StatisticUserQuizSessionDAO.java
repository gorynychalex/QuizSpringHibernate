package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.dao.QuizDAO;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gorynych on 12.04.17.
 *
 * This DAO provide unit 'suqs' - Statistic User Quiz Session
 * i.e. Statistic defined Session By User and By Quiz
 */

@Repository
public class StatisticUserQuizSessionDAO implements StatisticUserQuizSessionDAOInterface {

//    protected static final Logger logger = Logger.getLogger(StatisticUserQuizSessionDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }


    /**
     * Add statistic record
     *
     * @param suqs
     */
    public void addUserQuizSession(StatisticUserQuizSessionsInterface suqs) {
        //        logger.info(suqs.toString());
        currentSession().save(suqs);
    }

    @Override
    public void updateUserQuizSession(StatisticUserQuizSessionsInterface suqs) {
        currentSession().update(suqs);
    }

    public StatisticUserQuizSessions getBySessionId(int sessionId) {
        return currentSession().get(StatisticUserQuizSessions.class, sessionId);
    }

    @Override
    public List<StatisticUserQuizSessions> getStatisticByUser(int userId) {
        return currentSession().createQuery("from StatisticUserQuizSessions where user.id = " + userId).list();
    }

    @Override
    public List<Float> getResult(int sessionId) {
        return currentSession().get(StatisticUserQuizSessions.class,sessionId).getResult();
    }

    @Override
    public List<StatisticQuestions> getStatisticQuestionsBySessionId(int sessionId) {

//        StatisticUserQuizSessions statisticUserQuizSessions =
//                currentSession().get(StatisticUserQuizSessions.class,sessionId);
//        List<StatisticQuestions> statisticQuestionsList = new ArrayList<>();
//
//        for(StatisticQuestions statisticQuestion:statisticUserQuizSessions.getStatisticQuestionsList()){
//
//            statisticQuestion.getStatisticOptionsList().size();
//
//            statisticQuestionsList.add(statisticQuestion);
//        }
//
//        return statisticQuestionsList;

        return currentSession().get(StatisticUserQuizSessions.class,sessionId).
                getStatisticQuestionsList().
                stream().filter(x -> x.getStatisticOptionsList().size()>0).collect(Collectors.toList());
    }
}