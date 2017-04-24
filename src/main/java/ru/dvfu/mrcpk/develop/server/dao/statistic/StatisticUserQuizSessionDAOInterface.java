package ru.dvfu.mrcpk.develop.server.dao.statistic;

import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import java.util.List;

/**
 * Created by gorynych on 11.04.17.
 */
public interface StatisticUserQuizSessionDAOInterface {

    // Add session record
    void addUserQuizSession(StatisticUserQuizSessionsInterface suqs);

    StatisticUserQuizSessionsInterface getBySessionId(int sessionId);

    // Update session record
//    void updateUserQuizSession(StatisticUserQuizSessionsInterface suqs);

    // Get record by sessionid
//    StatisticUserQuizSessions getStatisticBySessionId(int sessionId);

    // Get entirely list of statistic
//    List<StatisticUserQuizSessions> getStatisticList();


    List<StatisticUserQuizSessions> getStatisticByUser(int userId);
//    List<StatisticUserQuizSessions> getStatisticByUser(QuizInterface quiz);
//
//    UserInterface getUserBySessionId(int sessionId);
//    QuizInterface getQuizBySessionId(int sessionId);

}
