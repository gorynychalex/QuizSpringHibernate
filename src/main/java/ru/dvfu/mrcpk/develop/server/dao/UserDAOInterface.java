package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.*;

import java.util.List;

public interface UserDAOInterface {
    List<UserAuth> list();
    UserAuth getById(String username);
    void add(UserAuth user);
    void update(UserAuth user) ;
    void remove(String user);
}
