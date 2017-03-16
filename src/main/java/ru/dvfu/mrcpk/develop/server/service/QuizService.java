package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.QuizDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;

import java.util.List;

@Service
public class QuizService implements QuizServiceInterface {

    @Autowired
    private QuizDAOInterface quizDAO;

    @Transactional
    public QuizInterface getQuizById(Number id) {
        return this.quizDAO.getQuizById(id);
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
}
