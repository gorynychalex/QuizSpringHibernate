package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

public interface UserAnswerServiceInterface {
    void setAnswer(UserAnswerOptions userAnswerOptions);
    void removeAnswerByQuestionId(Number questionId, Number sessionId);
}
