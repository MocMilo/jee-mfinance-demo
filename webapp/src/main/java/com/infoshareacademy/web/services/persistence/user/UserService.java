package com.infoshareacademy.web.services.persistence.user;

import com.infoshareacademy.web.model.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    void add(User user);

    User get(long userId);

    List<User> getUserByEmail(String userEmail);

    void update(User user);

    List<User> getAllUsers();

    void addDefaultAdminUser();

    void authorize(String email, HttpServletRequest req);
}
