package controller.rest;

import model.Kweet;
import service.KweetService;
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
public class KweetRESTController {

    @Inject
    private KweetService service;

    @GET
    public Response get(){
        return Response.ok(this.service.get()).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        try {
            return Response.ok(this.service.get(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response post(@Valid Kweet kweet,
                         @QueryParam("userId") long userId){
        try {
            return Response.ok(this.service.add(kweet, userId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{id}/like")
    public Response like(@PathParam("id") long id,
                         @QueryParam("userId") long userId){
        try {
            this.service.like(id, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("{id}/unlike")
    public Response unlike(@PathParam("id") long id,
                           @QueryParam("userId") long userId){
        try {
            this.service.unLike(id, userId);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}
