package ru.dvfu.mrcpk.develop.server.service.statistics;

import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import java.util.List;

/**
 * Created by gorynych on 12.04.17.
 */
public interface StatisticUserQuizSessionServiceInterface {

    void addUserQuizSession(int sessionId, User user, Quiz quiz);

    StatisticUserQuizSessionsInterface getBySessionId(int sessionId);

    List<StatisticUserQuizSessions> getStatisticByUser(int userId);

    List<Float> getResultBySessionId(int sessionId);

    List<StatisticQuestions> getStatisticQuestionsBySessionId(int sessionId);

    List<Float> getResult(int sessionId);

    User getUser();

}
