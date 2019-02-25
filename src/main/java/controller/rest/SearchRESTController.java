package controller.rest;

import model.Model;
import service.SearchService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
@Path("search")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SearchRESTController {

    private SearchService service;

    @GET
    @Path("{term}")
    public Collection<Model> search(@PathParam("term") String term){
        return this.service.search(term);
    }

}
