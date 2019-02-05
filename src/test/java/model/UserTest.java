package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Devin
 */
class UserTest {

    @Test
    void getUserName() {

        User u = new User(
                "UserName",
                "Password",
                "Bio",
                "Location",
                "Website",
                "UrlToWebsite",
                "Mail",
                UserRole.Regular);

        assertEquals("UserName", u.getUserName());

    }

}