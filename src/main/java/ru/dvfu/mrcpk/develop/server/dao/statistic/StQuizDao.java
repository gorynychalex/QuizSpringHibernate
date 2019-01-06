package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.dto.QuizResultDTO;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuiz;

@Repository
public class StQuizDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public boolean isExistSession(String session){
        return currentSession().get(StQuiz.class, session) != null;
    }

    public void addQuiz(String session, UserAuth user, Quiz quiz) {
        currentSession().save(new StQuiz(session, quiz, user));
    }

    public void addQuizResult(QuizResultDTO quizResultDTO){
        currentSession().save(new StQuiz(quizResultDTO));
    }
}
