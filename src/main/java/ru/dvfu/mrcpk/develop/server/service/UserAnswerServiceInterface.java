package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.util.List;

public interface UserAnswerServiceInterface {
    void setAnswer(UserAnswerOptions userAnswerOptions);
    void removeAnswerByQuestionId(Number questionId, String sessionId);
    List<UserAnswerOptions> getByQuestionAndSession(Number questionId, String sessionId);
    void updateByQuestionAndSession(Number questionId, Number optionid, String user, String sessionId);
    public void setAnswerSimple(Number questionId, List<Integer> options);
    List<Float> getResultList();
}
