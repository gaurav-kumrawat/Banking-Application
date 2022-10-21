package com.psl.banking.user;

import com.psl.banking.user.controller.UserController;
import com.psl.banking.user.entity.User;
import com.psl.banking.user.service.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@SpringBootTest
public class ControllerMackitoTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;


    @Test
    @Order(1)
    public void testAddUser() {
        User user=new User(6L,"Gaurav","pwd6","gaurav@kumrawat.com",80L);
        when(userService.createUser(user)).thenReturn(user);
        assertEquals(user,userController.createUser(user));
    }

    @Test
    @Order(2)
    public void testRemoveUser() {
        User user=new User(1L,"Gaurav","Kumrawat","gaurav@kumrawat.com",80L);
        long id=1;
        when(userService.getUser(id)).thenReturn(user);
        userService.deleteUser(2L);
        verify(userService,times(1)).deleteUser(2L);
    }

}