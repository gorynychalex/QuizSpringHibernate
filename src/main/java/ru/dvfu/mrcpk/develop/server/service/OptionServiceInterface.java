package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.OptionInterface;

import java.util.List;

public interface OptionServiceInterface {
    OptionInterface getById(Number id);
    List<OptionInterface> list();
    void add(OptionInterface option);
    void update(OptionInterface option);
    void remove(Number id);
}
