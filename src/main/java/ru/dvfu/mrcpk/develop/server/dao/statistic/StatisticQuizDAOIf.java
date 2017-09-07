package ru.dvfu.mrcpk.develop.server.dao.statistic;

import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.*;

import java.util.List;

/**
 * Created by gorynych on 25.04.17.
 */
public interface StatisticQuizDAOIf {

    //Statistic Quiz Interface

    StatisticQuizIf getStatisticQuizBySessionId(int sessionId);
    void addStatisticQuiz(StatisticQuizIf statisticQuiz);
    void updateStatisticQuiz(StatisticQuizIf statisticQuiz);
    List<StatisticQuizIf> getStatisticByUser(UserInterface user);
    List<StatisticQuizIf> getStatisticByQuiz(QuizInterface quiz);
    List<StatisticQuizIf> getStatistic();
    void setResult(StatisticQuizIf statisticQuiz);
    int getResult(StatisticQuizIf statisticQuiz);


    //Statistic Question interface

    StatisticQuestionsInterface getStatisticQuestionById(int id);
    void addStatisticQuestion(StatisticQuizIf statisticQuiz, StatisticQuestionsInterface statisticQuestion);
    List<StatisticQuestionsInterface> getStatisticQuestions();
    List<StatisticQuestionsInterface> getStatisticQuestionsByStatisticQuiz(StatisticQuizIf statisticQuiz);
    StatisticQuestionsInterface getStatisticQuestionByQuestion(QuestionInterface question);
    int getStatisticQuestionIdByQuestionAndStQuiz(StatisticQuizIf stQuiz, QuestionInterface question);
    List<Float> getMarksByStatisticQuestion(StatisticQuestionsInterface statisticQuestion);

    //Statistic Option interface

    StatisticOptionsInterface getStatisticOptionById(int id);
    void addStatisticOption(StatisticQuestionsInterface statisticQuestions, StatisticOptionsInterface statisticOption);
    List<StatisticOptionsInterface> getStatisticOptionsByQuestion(QuestionInterface question);
    List<StatisticOptionsInterface> getStatisticOptionsByQuiz(QuizInterface quiz);
    List<StatisticOptionsInterface> getStatisticOptionsByStQuestion(StatisticQuestionsInterface question);
    List<StatisticOptionsInterface> getStatisticOptionsByStStQuestion(StatisticQuizIf stQuiz);
}
