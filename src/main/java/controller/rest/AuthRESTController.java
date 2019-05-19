package controller.rest;

import controller.rest.bodies.CredentialBody;
import model.User;
import service.UserService;
import util.exceptions.NotFoundException;
import util.security.AuthenticationIdentityStore;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Created by Devin
 */
@Path("auth")
public class AuthRESTController {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserService userService;

    @GET
    public Response login() {
        if (securityContext.getCallerPrincipal() != null) {
            String id = securityContext.getCallerPrincipal().getName();
            try {
                User u = userService.get(id);
                return Response.ok(u).build();
            } catch (NotFoundException e) {
                return Response.status(NOT_FOUND).build();
            }
        }
        return Response.status(UNAUTHORIZED).build();
    }

}
