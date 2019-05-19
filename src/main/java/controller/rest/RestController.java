package controller.rest;

import model.Model;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;

public abstract class RestController {

    @Context
    private UriInfo uriInfo;

    void initLinks(Model model) {

        UriBuilder uriBuilder = this.uriInfo.getRequestUriBuilder();
        uriBuilder = uriBuilder.path(model.getId());
        Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder);
        Link selfLink = linkBuilder.rel("self").build();
        model.setLinks(Arrays.asList(selfLink));

    }
}
