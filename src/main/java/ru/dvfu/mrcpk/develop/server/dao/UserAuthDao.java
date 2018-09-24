package ru.dvfu.mrcpk.develop.server.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;

@Repository
public class UserAuthDao implements UserAuthDaoIf {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAuth findUserByUsername(String username) {
        return sessionFactory.getCurrentSession().get(UserAuth.class, username);
    }
}
