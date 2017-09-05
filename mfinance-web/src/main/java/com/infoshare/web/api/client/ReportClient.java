package com.infoshare.web.api.client;

import com.infoshare.web.adminpanel.trigger.ITriggerable;
import com.infoshare.web.webconfiguration.WebConfigurationProvider;
import com.infoshare.web.user.report.IUserActivityService;
import com.infoshare.web.user.report.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.util.List;

public class ReportClient implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportClient.class);
    private static final String HEADER_TOKEN = "token";
    private static final String TOKEN = "abcde";
    private IUserActivityService userActivityService;
    private WebConfigurationProvider webConfigurationProvider = new WebConfigurationProvider();


    public ReportClient() {
    }

    public ReportClient(IUserActivityService userActivityService){
        this.userActivityService = userActivityService;
    }

    public List<UserActivity> getAllUserActivity() {

        Client client = ClientBuilder.newClient();
        String uri = webConfigurationProvider
                .getConfiguration()
                .getSlaveModeAPIServiceTargetURI();
        WebTarget target = client.target(uri.concat("/users/activity"));

        LOGGER.info("Target uri: {}", target.getUri());

        Response response = target
                .request()
                .header(HEADER_TOKEN,TOKEN)
                .get();
        ResponseContainer responseContainer = response.readEntity(ResponseContainer.class);
        response.close();

        return responseContainer.getUserActivities();
    }

    @Override
    public void executeAction() {
        try {

            LOGGER.info("UserActivity list size {}",this.getAllUserActivity().size());
            userActivityService.updateAllUserActivityFromMaserAPI(this.getAllUserActivity());
        }catch (RuntimeException ex)
        {
            LOGGER.error("Failed to update from master API {}",ex.getMessage());
        }

        }

}
