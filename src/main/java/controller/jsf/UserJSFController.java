package controller.jsf;

import model.User;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Devin
 */
@Named
@RequestScoped
public class UserJSFController {

    private Collection<User> users;

    @Inject
    private UserService service;

    public Collection<User> getUsers() {
        return this.users;
    }

    @PostConstruct
    public void init() {
        users = this.service.get();
    }

}
