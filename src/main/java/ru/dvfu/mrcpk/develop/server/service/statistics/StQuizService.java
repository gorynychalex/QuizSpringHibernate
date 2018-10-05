package ru.dvfu.mrcpk.develop.server.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StQuestionDAO;
import ru.dvfu.mrcpk.develop.server.dao.statistic.StQuizDao;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuiz;

@Service
public class StQuizService {

    @Autowired
    private StQuizDao stQuizDao;


    @Autowired
    private StQuestionDAO stQuestionDao;

    @Transactional
    public boolean isExistSession(String session){
        return stQuizDao.isExistSession(session);
    }

    @Transactional
    public void addQuiz(String session, String user, Quiz quiz) {
        stQuizDao.addQuiz(session, user, quiz);
    }

    @Transactional
    public void addQuestion(int quizId, Question question){
        stQuestionDao.add(quizId, question);
    }

    @Transactional
    public void addQuestion(Quiz quiz, Question question){
        stQuestionDao.add(quiz, question);
    }
}
