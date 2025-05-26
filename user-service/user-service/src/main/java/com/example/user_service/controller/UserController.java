package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping
    public List<User> getAllUsers()
    {
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return  service.getUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user)
    {
        return service.addUser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        service.deleteUser(id);
    }
}
