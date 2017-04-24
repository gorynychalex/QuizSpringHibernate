package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.OptionInterface;
import ru.dvfu.mrcpk.develop.server.model.Question;

import java.util.List;

@Repository
public class OptionDAO implements OptionDAOInterface{

    protected static Logger logger = Logger.getLogger(OptionDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }


    public OptionInterface getById(Number id) {
        return currentSession().get(Option.class,id);
    }

    public List<OptionInterface> list() {
        return null;
    }

    public void add(Number questionId, OptionInterface option) {

        currentSession().save(option);

        Question question = currentSession().get(Question.class, questionId);

        question.getOptions().add((Option) option);

        currentSession().save(question);


    }

    public void update(OptionInterface option) {
        currentSession().update(option);
    }

    public void remove(Number id) {
        currentSession().delete(currentSession().get(Option.class,id));
    }
}
