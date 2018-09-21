package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserDAO;
import ru.dvfu.mrcpk.develop.server.dao.UserDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserDAOInterface userDAO;

    @Transactional
    public List<User> list() {
        return this.userDAO.list();
    }

    @Transactional
    public User getById(Number id) {
        return this.userDAO.getById(id);
    }

    @Transactional
    public void add(User user) {
        this.userDAO.add(user);
    }

    @Transactional
    public void update(User user) {
        this.userDAO.update(user);
    }

    @Transactional
    public void remove(Number id) {
        this.userDAO.remove(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.userDAO.list().get(0);


        return (UserDetails) user;

    }
}
