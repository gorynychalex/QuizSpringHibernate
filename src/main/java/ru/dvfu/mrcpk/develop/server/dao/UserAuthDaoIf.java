package ru.dvfu.mrcpk.develop.server.dao;


import ru.dvfu.mrcpk.develop.server.model.UserAuth;

public interface UserAuthDaoIf {

    UserAuth findUserByUsername(String username);

}
