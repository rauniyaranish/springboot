package com.springboot.controller;

import com.springboot.entity.JournalEntry;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all-user")
    public List<User> getAllUser() {
        return new ArrayList<>(userService.findAll());
    }

    @PostMapping("/create-admin-user")
    public void setUser(@RequestBody User user) {
        userService.saveAdminUser(user, "new");
    }
}
