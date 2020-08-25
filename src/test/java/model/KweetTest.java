package model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Devin
 */
public class KweetTest {

    @Test
    public void like() {

        Kweet k = Mock.kweet();
        User u = Mock.user();

        k.like(u);

        assertTrue(k.getLikers().contains(u));
        assertTrue(u.getLikedKweets().contains(k));

    }
}