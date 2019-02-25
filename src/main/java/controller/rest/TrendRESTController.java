package controller.rest;

import service.TrendService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Devin
 */
@Stateless
@Path("trends")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TrendRESTController {

    @Inject
    private TrendService trendService;

    @GET
    public Response get(@DefaultValue("10") @QueryParam("limit") int limit){
        return Response.ok(this.trendService.getPopular(limit)).build();
    }

}
