package com.infoshare.web.services.persistence.user;

import com.infoshare.web.model.user.User;
import com.infoshare.web.model.webconfiguration.WebConfiguration;
import com.infoshare.web.services.providers.WebConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserPersistence implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPersistence.class);

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
       User userToUpdate =  em.find(User.class, user.getId());
       userToUpdate.setFavourites(user.getFavourites());
       userToUpdate.setFavouriteInvestmentIndicators(user.getFavouriteInvestmentIndicators());
       userToUpdate.setAdmin(user.getAdmin());
       em.merge(userToUpdate);
    }

    @Override
    @Transactional
    public void addDefaultAdminUser(){

        /**
         * User with Admin role is added from properties in WebConfiguration.json file.
         */

        WebConfiguration webConfiguration= new WebConfigurationProvider().getWebConfigurationFromResources();

        String defaultAdminLogin = webConfiguration.getDefaultAdminAccountLogin();

        if(!this.getUserByEmail(defaultAdminLogin).isEmpty()){
          User user = this.getUserByEmail(defaultAdminLogin).stream()
                  .findAny()
                  .orElseThrow(NullPointerException::new);
          user.setAdmin(true);
          this.update(user);

        } else {
            User user = new User();
            user.setLogin(defaultAdminLogin);
            user.setAdmin(false);
            this.add(user);
        }
    }
}
