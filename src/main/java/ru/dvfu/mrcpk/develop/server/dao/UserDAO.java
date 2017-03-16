package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

@Repository
public class UserDAO implements UserDAOInterface{

    protected static Logger logger = Logger.getLogger(UserDAO.class);


    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<User> list() {
        logger.info("list users()");
        Query query = currentSession().createQuery("from User ");
        List<User> users = query.list();
        for(User user: users)
            logger.info("user id = " + user.getId() + ", name = " + user.getFirstname());
        return users;
    }


    public User getById(Number id) {
        logger.info("user getById");
        User user = currentSession().get(User.class,id);
        logger.info("user firstname: " + user.getFirstname());
        logger.info("user id: " + user.getId());
        return user;
    }

    public void add(User user) {

    }

    public void update(User user) {

    }

    public void remove(Number id) {

    }
}
