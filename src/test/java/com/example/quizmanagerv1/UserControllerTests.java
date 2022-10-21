package com.example.quizmanagerv1;


import com.example.quizmanagerv1.controllers.UserController;
import com.example.quizmanagerv1.model.User;
import com.example.quizmanagerv1.repositories.UserRepository;
import com.example.quizmanagerv1.security.Permission;
import com.example.quizmanagerv1.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User("Jon", "Jon@jon.com", "jon123", Permission.EDIT));
        usersList.add(new User("Steve", "Steve@steve.com", "Steve@!", Permission.RESTRICTED));
        usersList.add(new User("Bill", "Bill@bill.com", "Bill@!", Permission.VIEW));
        Mockito.when(userService.findAllUsers()).thenReturn(usersList);
        String url = "\"/api/v1\"";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
