package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.QuestionDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;

import java.sql.SQLException;
import java.util.List;

@Service
public class QuestionService implements QuestionServiceInterface {

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
    public void add(QuestionInterface question) {

    }

    @Transactional
    public void update(QuestionInterface question) {

    }

    @Transactional
    public void remove(Number id) {

    }
}
