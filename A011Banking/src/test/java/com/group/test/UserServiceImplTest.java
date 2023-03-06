package com.group.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.group.entity.User;
import com.group.model.persistence.UserDao;
import com.group.model.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("Test UserName", "Test Password");
    }

    @Test
    public void testAddUser() {
        when(userDao.save(testUser)).thenReturn(testUser);

        User addedUser = userService.addUser(testUser);

        assertNotNull(addedUser);
        assertEquals(testUser.getUserName(), addedUser.getUserName());
        assertEquals(testUser.getUserPassword(), addedUser.getUserPassword());
    }

    @Test
    public void testGetUserByUserName() {
        when(userDao.findById(testUser.getUserName())).thenReturn(Optional.of(testUser));

        User foundUser = userService.getUserbyUserName(testUser.getUserName());

        assertNotNull(foundUser);
        assertEquals(testUser.getUserName(), foundUser.getUserName());
        assertEquals(testUser.getUserPassword(), foundUser.getUserPassword());
    }

    @Test
    public void testLoginUser() {
        when(userDao.getUserByUserNameAndUserPassword(testUser.getUserName(), testUser.getUserPassword()))
                .thenReturn(testUser);

        User loggedInUser = userService.loginUser(testUser);

        assertNotNull(loggedInUser);
        assertEquals(testUser.getUserName(), loggedInUser.getUserName());
        assertEquals(testUser.getUserPassword(), loggedInUser.getUserPassword());
    }
}
