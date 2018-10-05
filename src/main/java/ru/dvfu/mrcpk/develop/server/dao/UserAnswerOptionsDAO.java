package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Option;
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

    public List getUserAnswersBySessionId(String sessionId) {
//        logger.info("getUserAnswersBySessionId(sessionId)");
        return sessionFactory.getCurrentSession().createQuery("from UserAnswerOptions as us WHERE us.sessionid = " + sessionId).list();
    }

    public void setAnswer(UserAnswerOptions userAnswerOptions) {
        currentSession().persist(userAnswerOptions);
    }

    public void removeAnswerByQuiestionId(Number questionId, String sessionId) {
        currentSession().createQuery("delete UserAnswerOptions as ua WHERE ua.questionid=" + questionId + " and ua.sessionid=" + sessionId).executeUpdate();
    }

    public List<UserAnswerOptions> getByQuestionAndSession(Number questionId, String sessionId) {
        return currentSession().createQuery("from UserAnswerOptions as uao WHERE uao.questionid = " + questionId.intValue() + " and uao.sessionid = " + sessionId).list();
    }


    public void updateByQuestionAndSession(Number questionId, Number optionId, String userId, String sessionId) {
        currentSession().createQuery(
                "update UserAnswerOptions as uao "
                        + "SET uao.optionid = " + optionId
                        + " WHERE uao.questionid = " + questionId
                        + " and uao.sessionid = " + sessionId + " and  uao.userid = " + userId ).executeUpdate();
    }

}
