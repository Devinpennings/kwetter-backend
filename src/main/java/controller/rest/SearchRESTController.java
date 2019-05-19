package controller.rest;

import controller.rest.bodies.ModelDTO;
import model.Model;
import service.SearchService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
@Path("search")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SearchRESTController {

    @Inject
    private SearchService service;

    @GET
    @Path("{term}")
    public Response search(@PathParam("term") String term){
        Collection<ModelDTO> dtos = new ArrayList<>();
        this.service.search(term).forEach(model -> {
            dtos.add(new ModelDTO(model));
        });
        return Response.ok(dtos).build();
    }

}
