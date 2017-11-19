package com.infoshare.web.services.reports.report;

import com.infoshare.web.services.reports.activity.IUserActivityService;
import com.infoshare.web.services.reports.activity.UserActivity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.infoshare.web.utils.ConstantsProvider.LINE_SEPARATOR;

@Stateless
public class CSVGenerator {

    @Inject
    private IUserActivityService userActivityService;

    public List<String> generateCSVLines(){

        List<UserActivity> userActivities =  new ArrayList<>(); /* = userActivityService.getAllUserActivityFromSlaveAPI();*/
        List<String> lines = new ArrayList<>();

        lines.add("Id, DateTime, Activity, Login, SessionId".concat(System.getProperty(LINE_SEPARATOR)));

        String separator = ",";
        for(UserActivity item : userActivities){

            StringBuilder sb = new StringBuilder();
            sb.append(item.getId()).append(separator);
            sb.append(item.getActivityDateTime().toString()).append(separator);
            sb.append(item.getActivityName()).append(separator);
            sb.append(item.getLogin()).append(separator);
            sb.append(item.getSessionId());
            sb.append(System.getProperty(LINE_SEPARATOR));
            lines.add(sb.toString());
        }
        return lines;
    }

}
