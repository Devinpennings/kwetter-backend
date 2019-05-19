package controller.rest;

import model.Kweet;
import service.KweetService;
import service.UserService;
import util.exceptions.NotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Devin
 */
@Stateless
@Path("kweets")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class KweetRESTController extends RestController {

    @Inject
    private KweetService service;

    @Inject
    private UserService userService;

    @GET
    public Response getPossibleUser(@QueryParam("userId") String userId) {
        if (userId == null) {
            return Response.ok(this.service.get()).build();
        }
        try {
            return Response.ok(userService.get(userId).getPostedKweets()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/timeline")
    public Response getTimeline(@QueryParam("userId") String userId) {
        try {
            return Response.ok(this.userService.getTimeline(userId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id){
        try {
            return Response.ok(this.service.get(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response post(@Valid Kweet kweet,
                         @QueryParam("userId") String userId){
        try {
            Kweet k = this.service.add(kweet, userId);
            this.initLinks(k);
            return Response.ok(k).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{id}/like")
    public Response like(@PathParam("id") String id,
                         @QueryParam("userId") String userId){
        try {
            this.service.like(id, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{id}/unlike")
    public Response unlike(@PathParam("id") String id,
                           @QueryParam("userId") String userId){
        try {
            this.service.unLike(id, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}
