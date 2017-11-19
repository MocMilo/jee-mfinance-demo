package com.infoshare.web.services.reports.activity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Default
public class PersistenceActivity implements IUserActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceActivity.class);

/*
    private ReportClient reportClient = new ReportClient();*/


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveActivity(UserActivity userActivity) {
        em.persist(userActivity);
    }

    @Override
    public List<UserActivity> getAllUserActivity() {
        List<UserActivity> activities = em.createQuery("select m from UserActivity m", UserActivity.class)
                .getResultList();
        return activities;
    }

/*
    @Override
    public List<UserActivity> getAllUserActivityFromSlaveAPI() {
        return reportClient.getAllUserActivity();
    }

*/

    @Override
    @Transactional
    public void updateAllUserActivityFromMaserAPI(List<UserActivity> activities) {

        em.createQuery("delete from UserActivity").executeUpdate();

        for (UserActivity item : activities) {

            UserActivity itemToPersist = new UserActivity();
            itemToPersist.setActivityDateTime(item.getActivityDateTime());
            itemToPersist.setActivityName(item.getActivityName());
            itemToPersist.setLogin(item.getLogin());
            itemToPersist.setSessionId(item.getSessionId());

            saveActivity(itemToPersist);

            LOGGER.info("UPDATE FROM MASTER API: {} {} {} {} {}", item.getId(),
                    item.getActivityDateTime(),
                    item.getActivityName(),
                    item.getLogin(),
                    item.getSessionId());
        }

    }
}
