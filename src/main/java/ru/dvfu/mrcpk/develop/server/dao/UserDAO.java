package ru.dvfu.mrcpk.develop.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.UserIf;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Repository
public class UserDAO implements UserDAOInterface{

    protected static final Logger logger = Logger.getLogger(UserDAO.class);


    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<UserAuth> list() {
        logger.info("list users()");
        Query query = currentSession().createQuery("from UserAuth ");
        List<UserAuth> users = query.getResultList();
        for(UserAuth user: users)
            logger.info("user name = " + user.getUsername());
        return users;
    }


    public UserAuth getById(String username) {
//        logger.info("user getById");
        UserAuth user = currentSession().get(UserAuth.class,username);

//        logger.info("user authorities: " + user.getAuthorities());
//        logger.info("user firstname: " + user.getFirstname());
        return user;
    }

    public void add(UserAuth user) {
        currentSession().persist(user);
    }

    public void update(UserAuth user) {
        currentSession().update(user);
//        currentSession().merge(user);
//
//        String userClassName = user.getClass().getName();
//
//        Class<?> userClass = Class.forName(userClassName);
//
//        logger.info(user.getClass().getName());

//        User existingUser = (User) currentSession().get(User.class, user.getId());

//        User existingUser = (User) currentSession().get(userClass, user.getId());
//
//        Method[] methods=user.getClass().getMethods();
//
//        for(Method method: methods){
//            if(!method.getName().matches("getClass(.*)") && method.getName().matches("^get(.*)"))
//            {
//                try {
//                    if(user.getClass().getMethod(method.getName()).invoke(user) != null)
//                    {
//
//                        String setMethodName = method.getName().replace("get","set");
//                        Method setMethod = existingUser.getClass().getMethod(setMethodName, method.getReturnType());
//
//                        String getMethodName = method.getName();
//                        Method getMethod = user.getClass().getMethod(getMethodName);
//
//                        setMethod.invoke(existingUser,getMethod.invoke(user));
//                        user.getClass().getMethod(method.getName().replace("get","set"), method.getReturnType()).invoke(existingUser,user.getClass().getMethod(method.getName()).invoke(user));
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
    }

    public void remove(String username) {
        currentSession().delete(currentSession().get(UserAuth.class,username));
    }
}
