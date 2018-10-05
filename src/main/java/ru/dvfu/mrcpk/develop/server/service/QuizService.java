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
    public QuizInterface getById(Number id) {
        return this.quizDAO.getQuizById(id);
    }

    @Transactional
    public QuizInterface getByIdLazy(Number id) {
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
    public List<Question> listQuestionByQuizId(Number quizId){
        return this.quizDAO.listQuestionByQuizId(quizId);
    }

    @Transactional
    @Override
    public void addStatistic(Number id, String sessionId, User user) {
        this.quizDAO.addStatistic(id,sessionId,user);
    }

    @Transactional
    public List<Float> getResultByQuizId(Number quizId, String sessionId) {

        logger.info("getResultByQuizId ( " + quizId.intValue() + ", " + sessionId + "); ");

        // Create List of Marks of every Questions
        List<Float> marks = new ArrayList<Float>();

        // Get List UserAnswers
        List userAnswerOptionss = userAnswerOptionsDAO.getUserAnswersBySessionId(sessionId);

        // Get Questions
        List<Question> questions = this.quizDAO.getQuizById(quizId).getQuestions();


        for(QuestionInterface question: questions){

            if(question.getOptions().size() > 0) {
                //mark = КВП/ОКП/(КВН + 1)
                int sumOptionsTrue = 0, sumAnsTrue = 0, sunAnsFalse = 0;

                for (Option option : question.getOptions()) {
                    logger.info("question id=" + question.getId() + "; option id = " + option.getId());
                    if (option.isCorrect()) sumOptionsTrue++;

                    for (UserAnswerOptions userAnswerOptions : (List<UserAnswerOptions>) userAnswerOptionss) {
                        //                logger.info("i = " + option.getId() + "; userans = " + userans);
//                        if (option.isCorrect() & option.getId().equals(userAnswerOptions.getOptionid())) {
                        if (option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
                            sumAnsTrue++;
                        }
                        if (!option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
                            sunAnsFalse++;
                        }

                    }
                }
//                logger.info("sumAnsTrue = " + sumAnsTrue + "; sumOptTrue = " + sumOptionsTrue + "; sumAnsFalse = " + sunAnsFalse);
                float mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sunAnsFalse + 1);
//                logger.info("mark = " + mark);
                marks.add(mark);
            } else { marks.add((float) 0);}
        }

        return marks;
    }
}
