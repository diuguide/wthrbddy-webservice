package com.home.weatherbuddy.service;

import com.home.weatherbuddy.model.User;
import com.home.weatherbuddy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password, String email) {
        logger.info("User registered: " + username);
        // need validation here
        userRepository.save(new User(username, email, password, "user"));
    }

    public User deleteUser(User user) {
        logger.info("User deleted: " + user);
        //need validation here
        userRepository.delete(user);
        return user;
    }

}
