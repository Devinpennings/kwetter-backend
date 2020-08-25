package integration;

import data.interfaces.IDAO;
import data.interfaces.IUserDAO;
import data.memory.ModelMemoryDAO;
import data.memory.UserMemoryDAO;
import model.Mock;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.UserService;
import util.exceptions.NotFoundException;

import javax.inject.Inject;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Devin
 */
@RunWith(Arquillian.class)
public class UserServiceIntegrationTest {

    @Deployment
    public static Archive<WebArchive> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(IUserDAO.class, IDAO.class, ModelMemoryDAO.class, UserMemoryDAO.class, UserService.class, User.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "WEB-INF/beans.xml");
    }

    @Inject
    private IUserDAO dao;

    @Inject
    private UserService service;

    @Test
    public void getById() {

        dao.get();

        Collection<User> mockUsers = Mock.users(10);
        User toGet = mockUsers.iterator().next();

        mockUsers.forEach(service::add);

        try {
            assertEquals(service.get(toGet.getId()), toGet);
        } catch (NotFoundException e) {
            fail();
        }

    }

    @Test
    public void get() {

        Collection<User> mockUsers = Mock.users(10);

        mockUsers.forEach(service::add);

        assertTrue(service.get().containsAll(mockUsers));

    }

    @Test
    public void getWithPagination() {
    }

    @Test
    public void update() {
    }

    @Test
    public void add() {
    }

    @Test
    public void follow() {
    }

    @Test
    public void unfollow() {
    }

}
