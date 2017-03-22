package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.lang.reflect.Method;
import java.util.List;

@Repository
public class UserAnswerOptionsDAO implements UserAnswerOptionsDAOInterface {


    protected static final Logger logger = Logger.getLogger(UserAnswerOptionsDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }


    public UserAnswerOptions getUserAnswerById(int id) {
        return null;
    }

    public List getUserAnswersBySessionId(int sessionId) {
        logger.info("getUserAnswersBySessionId(sessionId)");
        Query query = sessionFactory.getCurrentSession().createQuery("from UserAnswerOptions as us WHERE us.sessionid = " + sessionId);

        return query.list();

    }

    public void setAnswer(UserAnswerOptions userAnswerOptions) {
        currentSession().persist(userAnswerOptions);
    }

    public void removeAnswerByQuiestionId(Number questionId, Number sessionId) {
        currentSession().createQuery("delete UserAnswerOptions as ua WHERE ua.questionid=" + questionId + " and ua.sessionid=" + sessionId).executeUpdate();
    }
}
