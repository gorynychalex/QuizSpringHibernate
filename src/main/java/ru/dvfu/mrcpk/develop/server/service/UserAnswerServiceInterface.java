package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.util.List;

public interface UserAnswerServiceInterface {
    void setAnswer(UserAnswerOptions userAnswerOptions);
    void removeAnswerByQuestionId(Number questionId, Number sessionId);
    List<UserAnswerOptions> getByQuestionAndSession(Number questionId, Number sessionId);
    void updateByQuestionAndSession(Number questionId, Number optionid, Number userId, Number sessionId);
}
