package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;

import java.util.List;

@Repository
public class QuizDAO implements QuizDAOInterface{

    protected static final Logger logger = Logger.getLogger(QuizDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public QuizInterface getQuizById(Number id){
        QuizInterface quiz = currentSession().get(Quiz.class,id);
        quiz.getQuestions().size();
        return quiz;
    }

    public QuizInterface getQuizByIdLazy(Number id) {
        return currentSession().get(Quiz.class,id);
    }

    public void addQuiz(QuizInterface quiz) {
        logger.info("addQuiz(quiz)");
        currentSession().persist(quiz);
    }

    public List<Question> list(Number id){
        logger.info("getQuestionList()");
        QuizInterface quiz = currentSession().get(Quiz.class, id);
        return quiz.getQuestions();
    }

    public List<QuizInterface> list() {

        logger.info("getQuizList()");
        return currentSession().createQuery("from Quiz").list();
    }

    public void remove(Number id) {
        currentSession().delete(currentSession().get(Quiz.class,id));
    }


    @Override
    public void addStatistic(Number id, int sessionId, User user) {
        Quiz quiz = currentSession().get(Quiz.class,id.intValue());
//        quiz.addUserQuizSession(sessionId,user);
//        currentSession().update(quiz);
    }
}