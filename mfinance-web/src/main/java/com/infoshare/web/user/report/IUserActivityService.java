package com.infoshare.web.user.report;

import java.util.List;

public interface IUserActivityService {
   void saveActivity(UserActivity userActivity);

   List<UserActivity> getAllUserActivity();
   List<UserActivity> getAllUserActivityFromSlaveAPI();

   void updateAllUserActivityFromMaserAPI(List<UserActivity> activities);



}
