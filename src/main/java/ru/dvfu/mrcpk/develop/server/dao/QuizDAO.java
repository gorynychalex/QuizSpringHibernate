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

    public List<QuizInterface> list() {
        logger.info("getQuizList()");
        return currentSession().createQuery("from Quiz").list();
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

    @Override
    public void editQuiz(QuizInterface quiz) {

    }

    public void remove(Number id) {
        currentSession().delete(currentSession().get(Quiz.class,id));
    }

    public List<QuestionInterface> listQuestions(){
        return currentSession().createQuery("from Question ").list();
    }

    public List<Question> listQuestionByQuizId(Number quizId){
        logger.info("getQuestionList()");
        QuizInterface quiz = currentSession().get(Quiz.class, quizId);
        return quiz.getQuestions();
    }

    public QuestionInterface getQuestionById(Number id){
        logger.debug("Question getById(" + id +"):");
        return currentSession().get(Question.class, id);
    }

    public void addQuestion(Number quizId, QuestionInterface question) {

        currentSession().save(question);

        Quiz currentQuiz = currentSession().get(Quiz.class, quizId);

        currentQuiz.getQuestions().add((Question) question);

        currentQuiz.setQnums(currentQuiz.getQuestions().size());

        currentSession().save(currentQuiz);

    }

    public void updateQuestion(QuestionInterface question) {
        currentSession().update(question);
    }

    public void removeQuestion(Number id) {
        currentSession().delete(currentSession().get(Question.class,id));
    }


    //Option methods

    public List<OptionInterface> listOptions() {
        return null;
    }

    @Override
    public List<OptionInterface> listOptionsByQuizId(QuizInterface quiz) {
        return null;
    }

    @Override
    public List<OptionInterface> listOptionsByQuestionId(QuestionInterface question) {
        return null;
    }

    public OptionInterface getOptionById(Number id) {
        return currentSession().get(Option.class,id);
    }


    public void addOption(Number questionId, OptionInterface option) {

        currentSession().save(option);

        Question question = currentSession().get(Question.class, questionId);

        question.getOptions().add((Option) option);

        currentSession().save(question);
    }

    public void updateOption(OptionInterface option) {
        currentSession().update(option);
    }

    public void removeOption(Number id) {
        currentSession().delete(currentSession().get(Option.class,id));
    }

    //SHOULD REMOVE METHOD!!! STATISTICS -> statistic folder
    @Override
    public void addStatistic(Number id, int sessionId, User user) {
        Quiz quiz = currentSession().get(Quiz.class,id.intValue());
//        quiz.addUserQuizSession(sessionId,user);
//        currentSession().update(quiz);
    }
}