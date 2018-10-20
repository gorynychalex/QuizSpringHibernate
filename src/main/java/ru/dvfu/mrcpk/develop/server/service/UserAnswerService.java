package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserAnswerOptionsDAO;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gorynych on 18.03.17.
 */
@Service
public class UserAnswerService implements UserAnswerServiceInterface {

    @Autowired
    private QuestionServiceInterface questionService;

    @Autowired
    private UserAnswerOptionsDAO userAnswerOptionsDAO;

    private Map<Number, List<Integer>> useranswers = new HashMap<>();

    @Transactional
    public void setAnswer(UserAnswerOptions userAnswerOptions) {
        this.userAnswerOptionsDAO.setAnswer(userAnswerOptions);
    }

    @Transactional
    public void removeAnswerByQuestionId(Number questionId, String sessionId) {
        this.userAnswerOptionsDAO.removeAnswerByQuiestionId(questionId,sessionId);
    }

    @Transactional
    public List<UserAnswerOptions> getByQuestionAndSession(Number questionId, String sessionId) {
        return this.userAnswerOptionsDAO.getByQuestionAndSession(questionId,sessionId);
    }

    @Transactional
    public void updateByQuestionAndSession(Number questionId, Number optionid, String userId, String sessionId) {
        this.userAnswerOptionsDAO.updateByQuestionAndSession(questionId, optionid, userId, sessionId);
    }

    @Transactional
    public void setAnswerSimple(Number questionId, List<Integer> options){
        useranswers.put(questionId, options);
    }

    @Transactional
    public Map<Number, List<Integer>> getUseranswers() {
        return null;
    }

    @Transactional
    public List<Float> getResultList(){


        List<Float> result_list = new ArrayList<>();

        for(Map.Entry<Number,List<Integer>> entry: useranswers.entrySet()){
            result_list.add(this.questionService.getResult(entry.getKey(), entry.getValue()));
        }

        return result_list;
    }
}
