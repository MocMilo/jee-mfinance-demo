package com.infoshare.web.services.persistence.user.activityreport;

import java.util.List;

public interface IUserActivityService {
   void saveActivity(UserActivity userActivity);

   List<UserActivity> getAllUserActivity();


   void updateAllUserActivityFromMaserAPI(List<UserActivity> activities);



}
