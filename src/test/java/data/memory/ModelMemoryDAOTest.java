package data.memory;

import util.PaginationDetails;
import model.Mock;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Devin
 */
class ModelMemoryDAOTest {

    @Test
    void getAndAdd() {

        Collection<User> users = Mock.users(10);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();

        userDAO.add(users);
        assertTrue(userDAO.get().containsAll(users));

    }

    @Test
    void getWithPaginationLimit() {

        Collection<User> users = Mock.users(100);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(users);

        Collection<User> toGet = new ArrayList<>(users).subList(0, 10);

        PaginationDetails paginationDetails = new PaginationDetails(10);
        assertEquals(10, userDAO.get(paginationDetails).size());
        assertTrue(toGet.containsAll(userDAO.get(paginationDetails)));

    }

    @Test
    void getWithPaginationLimitAndPage() {

        Collection<User> users = Mock.users(100);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(users);

        Collection<User> toGet = new ArrayList<>(users).subList(20, 30);

        PaginationDetails paginationDetails = new PaginationDetails(10, 3);
        assertEquals(10, userDAO.get(paginationDetails).size());
        assertTrue(toGet.containsAll(userDAO.get(paginationDetails)));

        toGet = new ArrayList<>(users).subList(0, 20);

        PaginationDetails paginationDetails2 = new PaginationDetails(20, 1);
        assertEquals(20, userDAO.get(paginationDetails2).size());
        assertTrue(toGet.containsAll(userDAO.get(paginationDetails2)));

    }

    @Test
    void getWithIdAndAdd() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

    }

    @Test
    void update() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(user);

        String newEmail = "test@mail.com";
        user.setEmail(newEmail);
        userDAO.update(user);

        assertEquals(newEmail, userDAO.get(user.getId()).get().getEmail());

    }

    @Test
    void delete() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

        userDAO.delete(user);
        assertFalse(userDAO.get(user.getId()).isPresent());

    }
}