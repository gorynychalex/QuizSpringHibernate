package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserAnswerOptionsDAO;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

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
}
