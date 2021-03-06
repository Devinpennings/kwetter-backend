package data.memory;

import org.junit.Test;
import util.PaginationDetails;
import model.Mock;
import model.User;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Devin
 */
public class ModelMemoryDAOTest {

    @Test
    public void getAndAdd() {

        Collection<User> users = Mock.users(10);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(users);

        assertTrue(userDAO.get().containsAll(users));

    }

    @Test
    public void getWithPaginationLimit() {

        Collection<User> users = Mock.users(10);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(users);

        Collection<User> toGet = new ArrayList<>(users).subList(0, 10);

        PaginationDetails paginationDetails = new PaginationDetails(10);
        assertEquals(10, userDAO.get(paginationDetails).size());
        assertTrue(toGet.containsAll(userDAO.get(paginationDetails)));

    }

    @Test
    public void getWithPaginationLimitAndPage() {

        Collection<User> users = Mock.users(100);
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(users);

        ArrayList<User> toSort = new ArrayList<>(users);
        Collections.sort(toSort);
        List<User> toGet = toSort.subList(20, 30);

        PaginationDetails paginationDetails = new PaginationDetails(10, 3);
        Collection<User> result = userDAO.get(paginationDetails);

        assertEquals(10, userDAO.get(paginationDetails).size());
        assertTrue(result.containsAll(toGet));

        toGet = new ArrayList<>(toSort).subList(0, 20);

        PaginationDetails paginationDetails2 = new PaginationDetails(20, 1);
        assertEquals(20, userDAO.get(paginationDetails2).size());
        assertTrue(toGet.containsAll(userDAO.get(paginationDetails2)));

    }

    @Test
    public void getWithIdAndAdd() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

    }

    @Test
    public void update() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();
        userDAO.add(user);

        String newEmail = "test@mail.com";
        user.setEmail(newEmail);
        userDAO.update(user);

        assertEquals(newEmail, userDAO.get(user.getId()).get().getEmail());

    }

    @Test
    public void delete() {

        User user = Mock.user();
        ModelMemoryDAO<User> userDAO = new UserMemoryDAO();

        userDAO.add(user);
        assertEquals(user, userDAO.get(user.getId()).get());

        userDAO.delete(user);
        assertFalse(userDAO.get(user.getId()).isPresent());

    }
}