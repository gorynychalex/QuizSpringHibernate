package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.util.List;

@Repository
public class QuizDAO implements QuizDAOInterface{

    protected static final Logger logger = Logger.getLogger(QuizDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public QuizInterface getQuizById(Number id){
        return currentSession().get(Quiz.class,id);
    }

    @Transactional
    public void addQuiz(QuizInterface quiz) {
        logger.info("addQuiz(quiz)");
        currentSession().persist(quiz);
    }

    @Transactional
    public List<Question> list(Number id){
        logger.info("getQuestionList()");
        QuizInterface quiz = currentSession().get(Quiz.class, id);
        return quiz.getQuestions();
    }

    @Transactional
    public List<QuizInterface> list() {

        logger.info("getQuizList()");
        return currentSession().createQuery("from Quiz").list();
    }

    @Transactional
    public void remove(Number id) {
        currentSession().delete(currentSession().get(Quiz.class,id));
    }
}