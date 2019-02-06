package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void follow() {

        User u1 = Mock.user();
        User u2 = Mock.user();

        u1.follow(u2);

        assertTrue(u1.getFollowers().contains(u2));
        assertTrue(u2.getFollowing().contains(u1));

    }

    @Test
    void post() {

        User u = Mock.user();
        Kweet k = Mock.kweet();

        u.postKweet(k);

        assertTrue(u.getPostedKweets().contains(k));

    }



}