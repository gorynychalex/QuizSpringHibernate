package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuestion;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuiz;

@Repository
public class StQuestionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }



    public void add(Quiz currentQuiz, Question question){
        currentSession().save(new StQuestion(question));

//        Quiz currentQuiz = currentSession().get(Quiz.class, quizId);

        currentQuiz.getQuestions().add((Question) question);

        currentQuiz.setQnums(currentQuiz.getQuestions().size());

        currentSession().save(currentQuiz);
    }

    public void add(int quizId, Question question){
        currentSession().save(new StQuestion(question));

        StQuiz currentStQuiz = currentSession().get(StQuiz.class, quizId);

        currentStQuiz.getQuiz().getQuestions().add((Question) question);

        currentSession().save(currentStQuiz);
    }


}
