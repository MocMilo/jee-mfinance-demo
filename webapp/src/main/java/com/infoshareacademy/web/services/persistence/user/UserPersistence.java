package com.infoshareacademy.web.services.persistence.user;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.webconfiguration.WebConfiguration;
import com.infoshareacademy.web.services.providers.WebConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

public class UserPersistence implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPersistence.class);

    private static final String AUTH_USER = "authenticatedUser";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public User get(long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public List<User> getUserByEmail(String userEmail) {

        // TODO implement criteria
        return em.createQuery("select distinct m from User m where m.login=:login", User.class)
                .setParameter("login", userEmail)
                .getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(User user) {
        User userToUpdate = em.find(User.class, user.getId());
        userToUpdate.setFavouritesIVR(user.getFavouritesIVR());
        userToUpdate.setFavouritesIND(user.getFavouritesIND());
        userToUpdate.setAdmin(user.getAdmin());
        em.merge(userToUpdate);
    }

    @Override
    @Transactional
    public void addDefaultAdminUser() {

        /**
         * User with Admin role is added from properties in WebConfiguration.json file.
         */

        WebConfiguration webConfiguration = new WebConfigurationProvider().getWebConfigurationFromResources();

        String defaultAdminLogin = webConfiguration.getDefaultAdminAccountLogin();

        if (!this.getUserByEmail(defaultAdminLogin).isEmpty()) {
            User user = this.getUserByEmail(defaultAdminLogin).stream()
                    .findAny()
                    .orElseThrow(NullPointerException::new);
            user.setAdmin(true);
            this.update(user);

        } else {
            User user = new User();
            user.setLogin(defaultAdminLogin);
            user.setAdmin(true);
            this.add(user);
        }
    }

    @Override
    @Transactional
    public void authorize(String email, HttpServletRequest req) {
        User user = new User();
        if (this.getUserByEmail(email).isEmpty()) {
            user.setLogin(email);
            user.setAdmin(false);
            this.add(user);
        } else {
            user = this.getUserByEmail(email)
                    .stream()
                    .findAny()
                    .orElseThrow(NullPointerException::new);
        }
        req.getSession(true).setAttribute(AUTH_USER, user);
    }
}
