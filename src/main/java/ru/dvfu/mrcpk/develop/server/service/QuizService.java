package ru.dvfu.mrcpk.develop.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.QuizDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.UserAnswerOptionsDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService implements QuizServiceInterface {

    protected final static Logger logger = Logger.getLogger(QuizService.class);

    @Autowired
    private QuizDAOInterface quizDAO;

    @Autowired
    private UserAnswerOptionsDAOInterface userAnswerOptionsDAO;

    @Transactional
    public QuizInterface getQuizById(Number id) {
        return this.quizDAO.getQuizById(id);
    }

    @Transactional
    public QuizInterface getQuizByIdLazy(Number id) {
        return this.quizDAO.getQuizByIdLazy(id);
    }

    @Transactional
    public void addQuiz(QuizInterface quiz) {
        this.quizDAO.addQuiz(quiz);
    }

    @Transactional
    public List<QuizInterface> list() {
        return this.quizDAO.list();
    }

    @Transactional
    public void removeById(Number id) {
        this.quizDAO.remove(id);
    }


    @Transactional
    public List<Float> getResultByQuizId(Number id, Number sessionId) {

        logger.info("getResultByQuizId ( " + id.intValue() + ", " + sessionId.intValue() + "); ");

        List<Float> marks = new ArrayList<Float>();

        List userAnswerOptionss = userAnswerOptionsDAO.getUserAnswersBySessionId((Integer) sessionId);

        List<Question> questions = this.quizDAO.getQuizById(id).getQuestions();
        for(Question question: questions){

            if(question.getOptions().size() > 0) {
                //mark = КВП/ОКП/(КВН + 1)
                int sumOptionsTrue = 0, sumAnsTrue = 0, sunAnsFalse = 0;

                for (Option option : question.getOptions()) {
                    if (option.isCorrect()) sumOptionsTrue++;

                    for (UserAnswerOptions userAnswerOptions : (List<UserAnswerOptions>) userAnswerOptionss) {
                        //                logger.info("i = " + option.getId() + "; userans = " + userans);
                        if (option.isCorrect() & option.getId().equals(userAnswerOptions.getOptionid())) {
                            sumAnsTrue++;
                        }
                        if (!option.isCorrect() & option.getId().equals(userAnswerOptions.getOptionid())) {
                            sunAnsFalse++;
                        }

                    }
                }
                marks.add((float) sumAnsTrue / (float) sumOptionsTrue / ((float) sunAnsFalse + 1));
            } else { marks.add((float) 0);}
        }

        return marks;
    }
}
