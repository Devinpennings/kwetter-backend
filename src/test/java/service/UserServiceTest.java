package service;

import data.interfaces.IUserDAO;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Devin
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private IUserDAO userDAO;

    @Before
    public void before(){
        this.userService = new UserService();
        this.userService.setDAO(this.userDAO);
    }

    @Test
    public void add() {

        User u = model.Mock.user();

        Collection<User> mockReturn = new ArrayList<>();
        mockReturn.add(u);
        when(this.userDAO.get()).thenReturn(mockReturn);

        assertTrue(userService.get().contains(u));

    }

}
