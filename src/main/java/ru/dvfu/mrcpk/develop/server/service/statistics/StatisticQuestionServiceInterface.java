package ru.dvfu.mrcpk.develop.server.service.statistics;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;

/**
 * Created by gorynych on 18.04.17.
 */
public interface StatisticQuestionServiceInterface {

    void addStatisticQuestion(int sessionId, StatisticUserQuizSessions suqs, StatisticQuestions statisticQuestion);

    int getIdByQuestionAndSessionId(int sessionId, int questionId);
}
