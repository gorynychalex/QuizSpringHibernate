package ru.dvfu.mrcpk.develop.server.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.model.*;

import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDAO implements QuestionDAOInterface{


    protected static Logger logger = Logger.getLogger(QuestionDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public QuestionInterface getById(Number id){
        logger.debug("Question getById(" + id +"):");
        return currentSession().get(Question.class, id);
    }

    public List<QuestionInterface> list(){
        return currentSession().createQuery("from Question ").list();
    }

//        this.dataSource = dataSource;
//    public void setDataSource(DataSource dataSource){
//    @Autowired
//
//
//    public List<QuestionInterface> list1() {
//
//        return jdbcTemplate.query("SELECT * from Quiz.Question",
//                new RowMapper<QuestionInterface>() {
//                    public QuestionInterface mapRow(ResultSet resultSet, int i) throws SQLException {
//                        QuestionInterface questionTest1 = new Question();
//                        questionTest1.setId(resultSet.getInt("id"));
//                        questionTest1.setText(resultSet.getString("text"));
//                        return questionTest1;
//                    }
//                });
//    }

    public void add(Number quizId, QuestionInterface question) {

        currentSession().save(question);

        Quiz currentQuiz = currentSession().get(Quiz.class, quizId);

        currentQuiz.getQuestions().add((Question) question);

        currentQuiz.setQnums(currentQuiz.getQuestions().size());

        currentSession().save(currentQuiz);

    }


    public List<Question> list(Number quizid){
        logger.info("get list(quizid)");
        QuizInterface quiz = currentSession().get(Quiz.class,quizid);
        logger.info(quiz.getQuestions().size());
        return quiz.getQuestions();
    }

    public void update(QuestionInterface question) {
        currentSession().update(question);
    }

    public void remove(Number id) {
        currentSession().delete(currentSession().get(Question.class,id));
    }

//    private DataSource dataSource;
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setJdbcTemplate(DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    public JdbcTemplate getJdbcTemplate(){
//        return this.jdbcTemplate;
//    }

//    public QuizTest getByIdJDBC(Number id){
//
//        List<QuizTest> questionTest = getJdbcTemplate().query("SELECT * from Quiz.Question ",
//                new RowMapper<QuizTest>() {
//                    public QuizTest mapRow(ResultSet resultSet, int i) throws SQLException {
//                        QuizTest questionTest1 = new QuizTest();
//                        questionTest1.setId(resultSet.getInt("id"));
//                        questionTest1.setText(resultSet.getString("text"));
//                        questionTest1.setIdq(resultSet.getInt("quiz_id"));
//                        return questionTest1;
//                    }
//                });
//        return questionTest.get(0);
//    }

}