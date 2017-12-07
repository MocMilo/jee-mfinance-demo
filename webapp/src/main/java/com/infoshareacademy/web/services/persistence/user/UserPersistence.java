package com.infoshareacademy.web.services.persistence.user;

import com.infoshareacademy.web.model.session.SessionContainer;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.services.webconfiguration.WebConfigurationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

public class UserPersistence implements UserService {
    @Inject
    private WebConfigurationService configurationService;
    @Inject
    private SessionContainer sessionContainer;
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
         * User with Administrator role is added using properties from
         * resources/configuration/webconfiguration.json file.
         */
        String defaultAdminLogin = configurationService.get()
                .getDefaultAdminAccountLogin();

        if (!this.getUserByEmail(defaultAdminLogin).isEmpty()) {
            User user = this.getUserByEmail(defaultAdminLogin).stream()
                    .findAny()
                    .orElseThrow(NullPointerException::new);
            user.setAdmin(true);
            this.update(user);
        } else {
            User user = new User(defaultAdminLogin, true);
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
        sessionContainer.setUser(user);
        sessionContainer.setAuthenticated(true);
    }
}
