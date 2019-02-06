package model.Kweet;

import model.Kweet;
import model.Mock;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Devin
 */
class KweetTest {

    @Test
    void like() {

        Kweet k = Mock.kweet();
        User u = Mock.user();

        k.like(u);

        assertTrue(k.getLikers().contains(u));
        assertTrue(u.getLikedKweets().contains(k));

    }
}