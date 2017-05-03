package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticOptionsDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticQuestionsDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticUserQuizSessionDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gorynych on 12.04.17.
 */
@Service
public class StatisticUserQuizSessionService implements StatisticUserQuizSessionServiceInterface {

    /**
     *
     */
    @Autowired
    private StatisticUserQuizSessionDAOInterface suqsDAO;

    @Autowired
    private StatisticQuestionsDAOInterface squestionsDAO;

    @Autowired
    private StatisticOptionsDAOInterface soptionsDAO;

    @Transactional
    public void addUserQuizSession(int sessionId, User user, Quiz quiz) {
        StatisticUserQuizSessions suqs = new StatisticUserQuizSessions(sessionId,user,quiz);
        suqsDAO.addUserQuizSession(suqs);
    }

    @Transactional
    public StatisticUserQuizSessionsInterface getBySessionId(int sessionId) {
        return suqsDAO.getBySessionId(sessionId);
    }

    @Transactional
    @Override
    public List<StatisticUserQuizSessions> getStatisticByUser(int userId) {
        return suqsDAO.getStatisticByUser(userId);
    }

    @Transactional
    public List<Float> getResult(int sessionId){
        return suqsDAO.getResult(sessionId);
    }

    @Transactional
    public List<Float> getResultBySessionId(int sessionId){

        List<Float> result = new ArrayList<>();

        List<StatisticQuestions> statisticQuestions = suqsDAO.getStatisticQuestionsBySessionId(sessionId);

        for(StatisticQuestions squestion: statisticQuestions){
            if(squestion.getQuestion().getOptions().size() > 0) {
                //mark = КВП/ОКП/(КВН + 1)
                //mark = sumAnsTrue/sumOptionsTrue/(sumAnsFalse+1)
                int sumOptionsTrue = 0, sumAnsTrue = 0, sumAnsFalse = 0;

                for(StatisticOptions soption : squestion.getStatisticOptionsList()){
                    if(soption.getOption() != null && soption.getOption().isCorrect()) sumAnsTrue++;
                    if(soption.getOption() != null && !soption.getOption().isCorrect()) sumAnsFalse++;
                }

                for (Option option: squestion.getQuestion().getOptions()){
                    if(option != null && option.isCorrect()) sumOptionsTrue++;
                }

                float mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sumAnsFalse + 1);

                squestion.setMark(mark);

                result.add(mark);

            } else { result.add(0.0F);}
        }

        StatisticUserQuizSessionsInterface currentStatisticUserQuizSessions = (StatisticUserQuizSessionsInterface) suqsDAO.getBySessionId(sessionId);

        currentStatisticUserQuizSessions.setMark((float) result.stream().mapToDouble(i->i.floatValue()).sum());

        suqsDAO.updateUserQuizSession(currentStatisticUserQuizSessions);

        return result;
    }


    @Transactional
    @Override
    public List<StatisticQuestions> getStatisticQuestionsBySessionId(int sessionId) {
        return suqsDAO.getStatisticQuestionsBySessionId(sessionId);
    }


}
