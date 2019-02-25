package util.jaxrs;

import controller.rest.KweetRESTController;
import controller.rest.UserRESTController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Devin
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends ResourceConfig {

    public JAXRSConfiguration(){
        packages("org.glassfish.jersey.examples.jackson");
        register(JacksonFeature.class);
        register(ObjectMapperContextResolver.class);
        register(UserRESTController.class);
        register(KweetRESTController.class);
        register(UserRESTController.class);
    }


}
