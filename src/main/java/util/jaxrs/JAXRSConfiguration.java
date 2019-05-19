package util.jaxrs;

import controller.rest.*;
import io.jsonwebtoken.Jwts;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import util.security.*;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

import static util.security.Constants.ADMIN;
import static util.security.Constants.USER;

/**
 * Created by Devin
 */
@DeclareRoles({ADMIN, USER})
@ApplicationPath("api")
public class JAXRSConfiguration extends ResourceConfig {

    public JAXRSConfiguration(){
        packages("org.glassfish.jersey.examples.jackson");
        register(JacksonFeature.class);
        register(ObjectMapperContextResolver.class);
        register(UserRESTController.class);
        register(KweetRESTController.class);
        register(UserRESTController.class);
        register(SearchRESTController.class);
        register(ContainerResponseFilter.class);
        register(AuthRESTController.class);
        register(AuthenticationIdentityStore.class);
        register(AuthorizationIdentityStore.class);
        register(JWTHttpAuthenticationMechanism.class);
        register(JWTCredential.class);
        register(TokenProvider.class);
        register(Jwts.class);
    }

}
