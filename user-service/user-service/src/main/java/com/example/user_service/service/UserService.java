package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> getAllUser()
    {
        return repo.findAll();
    }
    public User getUser(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    public User addUser(User user)
        {
            return repo.save(user);
        }
        public void deleteUser(Long id)
        {
            repo.deleteById(id);
        }
}
