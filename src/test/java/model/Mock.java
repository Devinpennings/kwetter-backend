package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Devin
 */
public class Mock {

    public static User user(){
        return new User(
                "UserName",
                "Password",
                "Bio",
                "Location",
                "Website",
                "UrlToWebsite",
                "Mail");
    }

    public static Collection<User> users(int amount){
        ArrayList<User> users = new ArrayList<User>();
        for(int i = 0; i < amount; i++){
            users.add(Mock.user());
        }
        return users;
    }

    public static Kweet kweet(){
        return new Kweet("Dit is een kweet");
    }

    public static Collection<Kweet> kweets(int amount){
        ArrayList<Kweet> kweets = new ArrayList<Kweet>();
        for(int i = 0; i < amount; i++){
            kweets.add(Mock.kweet());
        }
        return kweets;
    }

}
