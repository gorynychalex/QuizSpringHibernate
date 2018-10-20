package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserDAO;
import ru.dvfu.mrcpk.develop.server.dao.UserDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

    @Autowired
    private UserDAOInterface userDAO;

    @Transactional
    public List<UserAuth> list() {
        return this.userDAO.list();
    }

    @Transactional
    public UserAuth getById(String id) {
        return this.userDAO.getById(id);
    }

    @Transactional
    public void add(UserAuth user) {
        this.userDAO.add(user);
    }

    @Transactional
    public void update(UserAuth user) {
        this.userDAO.update(user);
    }

    @Transactional
    public void remove(String id) {
        this.userDAO.remove(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserAuth userAuth = this.userDAO.getById(s);

//        return (UserDetails) user;

//        logger.info("username = " + username + ", getUsername() = " + userAuth.getUsername() + ", getPasswd() = " + userAuth.getPassword());

        User.UserBuilder builder = null;

        if(userAuth != null){
            builder = org.springframework.security.core.userdetails.User.withUsername(userAuth.getUsername());
            builder.disabled(!userAuth.isEnabled());
            builder.password(userAuth.getPassword());
//            String[] authorities = (String[]) userAuth.getAuthorities()
//                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);
            String[] authorities = new String[]{"ROLE_USER"};
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return builder.build();
    }
}
