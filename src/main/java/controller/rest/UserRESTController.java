package controller.rest;

import model.Model;
import util.PaginationDetails;
import model.User;
import service.UserService;
import util.exceptions.NotFoundException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

import static util.security.Constants.ADMIN;
import static util.security.Constants.USER;

/**
 * Created by Devin
 */
@Stateless
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTController extends RestController {

    @Inject
    private UserService service;

    @Context
    private UriInfo uriInfo;

    @GET
    @RolesAllowed({ USER, ADMIN })
    public Response get(@DefaultValue("-1") @QueryParam("limitPerPage") int limitPerPage,
                        @DefaultValue("-1") @QueryParam("currentPage") int currentPage){

        PaginationDetails paginationDetails = null;

        if (limitPerPage != -1 && currentPage != -1) {
            paginationDetails = new PaginationDetails(limitPerPage, currentPage);
        }

        else if (limitPerPage != -1){
            paginationDetails = new PaginationDetails(limitPerPage);
        }

        if (paginationDetails != null)  {
            return Response.ok(this.service.get()).build();
        }

        return Response.ok(this.service.get()).build();

    }

    @GET
    @Path("{id}")
    @RolesAllowed({ ADMIN, USER })
    public Response get(
            @PathParam("id") String id){
        try {
            return Response.ok(service.get(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @RolesAllowed({ ADMIN, USER })
    public Response put(@Valid User user){
        Optional<User> result = this.service.update(user);
        if (result.isPresent()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @PermitAll
    public Response post(@Valid User user){
        User u = this.service.add(user);
        this.initLinks(u);
        return Response.status(Response.Status.CREATED).entity(u).build();
    }

    @POST
    @Path("{id}/follow")
    @RolesAllowed({ ADMIN, USER })
    public Response follow(@PathParam("id") String userId,
                           @QueryParam("follower") String followerId){

        try {
            this.service.follow(followerId, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("{id}/unfollow")
    @RolesAllowed({ ADMIN, USER })
    public Response unfollow(@PathParam("id") String userId,
                             @QueryParam("follower") String followerId){

        try {
            this.service.unfollow(followerId, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
