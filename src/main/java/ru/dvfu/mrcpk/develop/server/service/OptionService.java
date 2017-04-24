package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.OptionDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.OptionInterface;

import java.util.List;

@Service
public class OptionService implements OptionServiceInterface{

    @Autowired
    private OptionDAOInterface optionDAO;

    @Transactional
    public OptionInterface getById(Number id) {
        return optionDAO.getById(id);
    }

    @Transactional
    public List<OptionInterface> list() {
        return null;
    }

    @Transactional
    public void add(Number questionId, OptionInterface option) {

        this.optionDAO.add(questionId, option);
    }

    @Transactional
    public void update(OptionInterface option) {

        this.optionDAO.update(option);
    }

    @Transactional
    public void remove(Number id) {

        this.optionDAO.remove(id);
    }
}
