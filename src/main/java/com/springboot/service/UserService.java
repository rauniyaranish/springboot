package com.springboot.service;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    /*public User save(User user) {
        return userRepository.save(user);
    }*/

    public User save(User user, String userType) {
        try {
            if (userType.equals("new")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            log.info("Info {}", e.getMessage());
            log.warn("Warn {}", e.getMessage());
            log.trace("Trace {}", e.getMessage());
            log.debug("Debug {}", e.getMessage());
            return null;
        }
    }

    public User saveAdminUser(User user, String userType) {
        if (userType.equals("new")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
        }
        user.setDate(new Date());
        return userRepository.save(user);
    }

    public boolean testSave(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

     public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
     }

     public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
     }
}
