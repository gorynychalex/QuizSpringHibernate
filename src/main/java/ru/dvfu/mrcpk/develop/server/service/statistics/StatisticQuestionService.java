package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticQuestionsDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;

/**
 * Created by gorynych on 18.04.17.
 */
@Service
public class StatisticQuestionService implements StatisticQuestionServiceInterface{

    @Autowired
    private StatisticQuestionsDAOInterface suqsi;

    @Transactional
    public void addStatisticQuestion(int sessionId, StatisticUserQuizSessions suqs, StatisticQuestions statisticQuestion){
        suqsi.addStatisticQuestion(sessionId, suqs,statisticQuestion);
    }

    @Transactional
    public int getIdByQuestionAndSessionId(int sessionId, int questionId) {
        return suqsi.getIdByQuestionAndSessionId(sessionId,questionId);
    }
}
