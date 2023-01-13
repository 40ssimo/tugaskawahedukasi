package id.kawahedukasi.controller;

import id.kawahedukasi.service.NotificationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationController {
    @Inject
    NotificationService notificationService;

    @GET
    @Path("/sendEmail")
    public Response sendEmail() {
        notificationService.sendEmail();
        return Response.ok().build();
    }
}
