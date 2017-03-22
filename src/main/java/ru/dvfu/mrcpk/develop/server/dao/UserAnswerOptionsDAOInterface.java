package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.util.List;

public interface UserAnswerOptionsDAOInterface {
//    User getUserById(int userid);
//    Option getOptionById(long optionid);
//    void setUser(User user);
//    void setOption(Option option);
    UserAnswerOptions getUserAnswerById(int id);
    List getUserAnswersBySessionId(int sessionId);
    void setAnswer(UserAnswerOptions userAnswerOptions);
    void removeAnswerByQuiestionId(Number questionId, Number sessionId);
}
