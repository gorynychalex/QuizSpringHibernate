package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StatisticUserQuizSessionDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

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

}
