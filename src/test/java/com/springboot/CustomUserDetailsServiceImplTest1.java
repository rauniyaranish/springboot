package com.springboot;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.CustomUserDetailServiceImpl;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class CustomUserDetailsServiceImplTest1 {

    @Autowired
    private CustomUserDetailServiceImpl userDetailService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUserByUsername() {
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(User.builder().username("anish").password("asdfasdfas").roles(new ArrayList<>()).build()));
        UserDetails userDetails = userDetailService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }
}
