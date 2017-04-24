package ru.dvfu.mrcpk.develop.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.OptionDAO;
import ru.dvfu.mrcpk.develop.server.dao.OptionDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.QuestionDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.*;

import java.sql.SQLException;
import java.util.List;

@Service
public class QuestionService implements QuestionServiceInterface {

    protected static final Logger logger = Logger.getLogger(QuestionService.class);

    @Autowired
    private QuestionDAOInterface questionDAO;

    @Transactional
    public QuestionInterface getById(Number id) {
        return this.questionDAO.getById(id);
    }

    @Transactional
    public List<QuestionInterface> list(Number quizid) {
        return this.questionDAO.list();
    }

    @Transactional
    public void add(Number quizId, QuestionInterface question) {
        this.questionDAO.add(quizId, question);
    }

    @Transactional
    public void update(QuestionInterface question) {

    }

    @Transactional
    public void remove(Number id) {

        this.questionDAO.remove(id);
    }

    @Transactional
    public float getResult(Number questionid, List<Integer> userAnswers) {

//        logger.info("getResult() byQuestionId = " + questionid.intValue());

        List<Option> options = this.questionDAO.getById(questionid).getOptions();

//        logger.info("getResult() options size: " + options.size());

        //mark = КВП/ОКП/(КВН + 1)
        int sumOptionsTrue=0,sumAnsTrue=0,sunAnsFalse=0;

        for (Option option : options) {
            if (option.isCorrect()) sumOptionsTrue++;
            for(Integer userans: userAnswers) {
//                logger.info("i = " + option.getId() + "; userans = " + userans);
//                if (option.isCorrect() & option.getId().equals(userans)) {
                if (option.isCorrect() & option.getId() == userans) {
                    sumAnsTrue++;
                }
//                if (!option.isCorrect() & option.getId().equals(userans)) {
                if (!option.isCorrect() & option.getId() == userans) {
                    sunAnsFalse++;
                }
            }
        }

//        logger.info("sumOptionsTrue: " + sumOptionsTrue);
//        logger.info("sumAnsTrue: " + sumAnsTrue);
//        logger.info("sumAnsFalse: " + sunAnsFalse);

        float mark = (float)sumAnsTrue/(float)sumOptionsTrue/((float)sunAnsFalse+1);

        return mark;

    }
}
