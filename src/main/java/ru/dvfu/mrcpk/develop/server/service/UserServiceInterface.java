package ru.dvfu.mrcpk.develop.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    List<User> list();
    User getById(Number id);
    void add(User user);
    void update(User user);
    void remove(Number id);
}
