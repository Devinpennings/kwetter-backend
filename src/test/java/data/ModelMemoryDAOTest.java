package data;

import data.memory.ModelMemoryDAO;
import data.memory.UserMemoryModelMemoryDAO;
import model.Mock;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Devin
 */
class ModelMemoryDAOTest {

    @Test
    void getAndAdd() {

        Collection<User> users = Mock.users(10);
        ModelMemoryDAO<User> userDAO = new UserMemoryModelMemoryDAO();

        userDAO.add(users);
        assertTrue(userDAO.get().containsAll(users));

    }

    @Test
    void getWithIdAndAdd() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryModelMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

    }

    @Test
    void update() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryModelMemoryDAO();
        userDAO.add(user);

        String newEmail = "test@mail.com";
        user.setEmail(newEmail);
        userDAO.update(user);

        assertEquals(newEmail, userDAO.get(user.getId()).get().getEmail());

    }

    @Test
    void delete() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryModelMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

        userDAO.delete(user);
        assertFalse(userDAO.get(user.getId()).isPresent());

    }
}