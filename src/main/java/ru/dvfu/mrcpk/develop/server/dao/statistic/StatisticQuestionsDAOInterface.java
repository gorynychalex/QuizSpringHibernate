package ru.dvfu.mrcpk.develop.server.dao.statistic;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gorynych on 12.04.17.
 *
 * @class
 */
public interface StatisticQuestionsDAOInterface extends Serializable{

    StatisticQuestionsInterface getById(int id);

    /**
     * Method performs statistic question
     *
     * @param suqs
     * @param statisticQuestion
     */
    void addStatisticQuestion(String sessionId, StatisticUserQuizSessions suqs, StatisticQuestions statisticQuestion);

    List<StatisticQuestions> getStatisticQuestionList();

    List<StatisticQuestions> getStatisticQuestionListBySessionId(String sessionId);

    int getIdByQuestionAndSessionId(String sessionId, int questionId);

    StatisticQuestionsInterface getStatisticQuestionById(int id);

    StatisticUserQuizSessionsInterface getStatisticsByQuestion(Question question);

    List<Float> getResultsBySessionId(String sessionId);

}
