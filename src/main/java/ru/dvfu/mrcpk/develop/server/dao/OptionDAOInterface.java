package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.OptionInterface;

import java.util.List;

public interface OptionDAOInterface {
    OptionInterface getById(Number id);
    List<OptionInterface> list();
    void add(OptionInterface option);
    void update(OptionInterface option);
    void remove(Number id);
}
