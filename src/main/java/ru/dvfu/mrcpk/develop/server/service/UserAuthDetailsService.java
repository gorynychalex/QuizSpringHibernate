package ru.dvfu.mrcpk.develop.server.service;

//import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.controller.QuestionController;
import ru.dvfu.mrcpk.develop.server.dao.UserAuthDao;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;


//https://www.baeldung.com/spring-security-authentication-with-a-database

//@Slf4j
@Service("userAuthDetailsService")
public class UserAuthDetailsService implements UserDetailsService {

    protected static final Logger logger = Logger.getLogger(UserAuthDetailsService.class);

    @Autowired
    private UserAuthDao userAuthDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,NullPointerException {

        UserAuth userAuth = userAuthDao.findUserByUsername(username);

//        logger.info("username = " + username + ", getUsername() = " + userAuth.getUsername() + ", getPasswd() = " + userAuth.getPassword());

        User.UserBuilder builder = null;

        if(userAuth != null){
           builder = org.springframework.security.core.userdetails.User.withUsername(userAuth.getUsername());
            builder.disabled(!userAuth.isEnabled());
            builder.password(userAuth.getPassword());
//            String[] authorities = userAuth.getAuthorities()
//                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);
            String[] authorities = new String[]{"ROLE_USER"};
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return builder.build();
    }


}
