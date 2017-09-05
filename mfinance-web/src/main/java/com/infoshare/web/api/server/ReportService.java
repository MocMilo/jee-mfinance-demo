package com.infoshare.web.api.server;

import com.infoshare.web.user.report.IUserActivityService;
import com.infoshare.web.user.report.UserActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class ReportService {

@Inject
private IUserActivityService userActivityService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
    private static final String HEADER_TOKEN = "token";
    private static final String TOKEN = "abcde";

    @GET
    @Path("/activity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoginForm(@Context HttpHeaders headers) throws JsonProcessingException {

       List<UserActivity> userActivityList= userActivityService.getAllUserActivity();


       if(!headers.getRequestHeader(HEADER_TOKEN).isEmpty()) {
           String token = headers.getRequestHeader(HEADER_TOKEN).get(0);
           if(TOKEN.equals(token)){
               ObjectMapper mapper = new ObjectMapper();

               ReportContainer reportContainer= new ReportContainer();
               reportContainer.setUserActivities(userActivityList);

               String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportContainer);

               LOGGER.info("Request:../api/users/activity succeeded!");

               return Response.ok(result).build();
           }
           else {
               LOGGER.info("Bad token value: {}", token );
               return Response.status(401).build();
           }
       }
       else {
           LOGGER.info("Bad token: null");
           return Response.status(401).build();
       }
    }
}
