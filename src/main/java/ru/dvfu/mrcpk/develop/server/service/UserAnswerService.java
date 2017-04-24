package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserAnswerOptionsDAO;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.util.List;

/**
 * Created by gorynych on 18.03.17.
 */
@Service
public class UserAnswerService implements UserAnswerServiceInterface {

    @Autowired
    private UserAnswerOptionsDAO userAnswerOptionsDAO;

    @Transactional
    public void setAnswer(UserAnswerOptions userAnswerOptions) {
        this.userAnswerOptionsDAO.setAnswer(userAnswerOptions);
    }

    @Transactional
    public void removeAnswerByQuestionId(Number questionId, Number sessionId) {
        this.userAnswerOptionsDAO.removeAnswerByQuiestionId(questionId,sessionId);
    }

    @Transactional
    public List<UserAnswerOptions> getByQuestionAndSession(Number questionId, Number sessionId) {
        return this.userAnswerOptionsDAO.getByQuestionAndSession(questionId,sessionId);
    }

    @Transactional
    public void updateByQuestionAndSession(Number questionId, Number optionid, Number userId, Number sessionId) {
        this.userAnswerOptionsDAO.updateByQuestionAndSession(questionId, optionid, userId, sessionId);
    }
}
