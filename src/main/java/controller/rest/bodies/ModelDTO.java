package controller.rest.bodies;

import model.Kweet;
import model.Model;
import model.Trend;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class ModelDTO {

    private static final Map<Class, String> typeMap = new HashMap<>();

    static {
        typeMap.put(User.class, "user");
        typeMap.put(Kweet.class, "kweet");
        typeMap.put(Trend.class, "trend");
    }

    public String type;
    public Model model;

    public ModelDTO(Model model) {
        this.model = model;
        this.type = typeMap.get(model.getClass());
    }

}
