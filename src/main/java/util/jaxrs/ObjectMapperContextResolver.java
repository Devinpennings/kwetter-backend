package util.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy '00:00:00' 'GMT'Z '('z')'");
        mapper.setDateFormat(df);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) { return mapper; }

}