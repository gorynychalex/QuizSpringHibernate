package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuizIf;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gorynych on 25.04.17.
 */
@Repository
public class StatisticQuizDAO implements StatisticQuizDAOIf{

//    protected static final Logger logger = Logger.getLogger(StatisticQuizDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }


    //Statistic Quiz Interface

    @Override
    public StatisticQuizIf getStatisticQuizBySessionId(int sessionId) {
        return null;
    }

    @Override
    public void addStatisticQuiz(StatisticQuizIf statisticQuiz) {

    }

    @Override
    public void updateStatisticQuiz(StatisticQuizIf statisticQuiz) {

    }

    @Override
    public List<StatisticQuizIf> getStatisticByUser(UserInterface user) {
        return null;
    }

    @Override
    public List<StatisticQuizIf> getStatisticByQuiz(QuizInterface quiz) {
        return null;
    }

    @Override
    public List<StatisticQuizIf> getStatistic() {
        return null;
    }

    @Override
    public void setResult(StatisticQuizIf statisticQuiz) {

    }

    @Override
    public int getResult(StatisticQuizIf statisticQuiz) {
        return 0;
    }

    //Statistic Question Interface

    @Override
    public StatisticQuestionsInterface getStatisticQuestionById(int id) {
        return null;
    }

    @Override
    public void addStatisticQuestion(StatisticQuizIf statisticQuiz, StatisticQuestionsInterface statisticQuestion) {

    }

    @Override
    public List<StatisticQuestionsInterface> getStatisticQuestions() {
        return null;
    }

    @Override
    public List<StatisticQuestionsInterface> getStatisticQuestionsByStatisticQuiz(StatisticQuizIf statisticQuiz) {
        return null;
    }

    @Override
    public StatisticQuestionsInterface getStatisticQuestionByQuestion(QuestionInterface question) {
        return null;
    }

    @Override
    public int getStatisticQuestionIdByQuestionAndStQuiz(StatisticQuizIf stQuiz, QuestionInterface question) {
        return 0;
    }

    @Override
    public List<Float> getMarksByStatisticQuestion(StatisticQuestionsInterface statisticQuestion) {
        return null;
    }

    //Statistic Option Interface

    @Override
    public StatisticOptionsInterface getStatisticOptionById(int id) {
        return null;
    }

    @Override
    public void addStatisticOption(StatisticQuestionsInterface statisticQuestions, StatisticOptionsInterface statisticOption) {

    }

    @Override
    public List<StatisticOptionsInterface> getStatisticOptionsByQuestion(QuestionInterface question) {
        return null;
    }

    @Override
    public List<StatisticOptionsInterface> getStatisticOptionsByQuiz(QuizInterface quiz) {
        return null;
    }

    @Override
    public List<StatisticOptionsInterface> getStatisticOptionsByStQuestion(StatisticQuestionsInterface question) {
        return null;
    }

    @Override
    public List<StatisticOptionsInterface> getStatisticOptionsByStStQuestion(StatisticQuizIf stQuiz) {
        return null;
    }
}
