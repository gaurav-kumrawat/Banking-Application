package com.psl.banking.user;

import com.psl.banking.user.entity.User;
import com.psl.banking.user.repository.UserRepository;
import com.psl.banking.user.service.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceMackito {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;
    public List<User>  users;
    @Test
    @Order(1)
    public void testAddUser(){

        User user = new User(1L,"Gaurav","pwd1","gaurav@gmail.com",80L);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.createUser(user));
    }

    @Test
    @Order(2)
    public  void testGetUserById(){
        long userId = 2;
        User user2 = new User(2L,"Gaurav","pwd1","gaurav@gmail.com",80L);
        when(userRepository.findByUserId(userId)).thenReturn(user2);
        assertEquals(userId,userRepository.findByUserId(userId).getUserId());
    }

    @Test
    @Order(3)
    public void testGetUpdatedUser(){
        long userId = 3;
        User user3 = new User(3L,"Gaurav","pwd1","gaurav@gmail.com",80L);
        when(userRepository.findByUserId(userId)).thenReturn(user3);
        User user = userRepository.findByUserId(userId);
        user.setEmailId("gauravkumrawat@gmail.com");
        when(userRepository.save(user)).thenReturn(user);
        User userUpdated = userRepository.save(user);
        assertEquals(userUpdated.getEmailId(),"gauravkumrawat@gmail.com");
    }

    @Test
    @Order(4)
    public void testDeleteUserById(){

        long userId = 3L;
        User user3 = new User(1L,"Gaurav","pwd1","gaurav@gmail.com",80L);
        userService.deleteUser(2L);
        verify(userRepository,times(1)).deleteById(2L);
    }




}