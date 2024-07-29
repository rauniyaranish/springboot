package com.springboot.controller;

import com.springboot.entity.JournalEntry;
import com.springboot.entity.JournalEntryV2;
import com.springboot.entity.User;
import com.springboot.exception.ResponseNotFound;
import com.springboot.service.JournalEntryService;
import com.springboot.service.JournalEntrySqlService;
import com.springboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return new ArrayList<>(userService.findAll());
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody User user) {
        Optional<User> oldUser = userService.findByUsername(user.getUsername());
        if (oldUser.isPresent()) {
            oldUser.get().setPassword(user.getPassword());
            userService.save(oldUser.get(), "new");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseNotFound("username not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> oldUser = userService.findByUsername(username);
        if (oldUser.isPresent()) {
            oldUser.get().setUsername(user.getUsername());
            oldUser.get().setPassword(user.getPassword());
            userService.save(oldUser.get(), "new");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseNotFound("username not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}