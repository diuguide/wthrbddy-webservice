package com.home.weatherbuddy.controller;

import com.home.weatherbuddy.model.User;
import com.home.weatherbuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody User user) {
        logger.info("User registered: " + user);
        return user;
    }

    @DeleteMapping(value = "/delete")
    public User deleteUser(@RequestBody User user) {
        logger.info("User deleted: " + user);
        logger.info(userService.deleteUser(user).toString());
        return user;
    }
}