package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    List<UserAuth> list();
    UserAuth getById(String id);
    void add(UserAuth user);
    void update(UserAuth user);
    void remove(String id);
}
