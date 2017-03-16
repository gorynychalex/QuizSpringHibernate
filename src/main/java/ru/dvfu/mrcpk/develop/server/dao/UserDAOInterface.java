package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

public interface UserDAOInterface {
    List<User> list();
    User getById(Number id);
    void add(User user);
    void update(User user);
    void remove(Number id);
}
