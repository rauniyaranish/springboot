package com.springboot;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.CustomUserDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

public class CustomUserDetailsServiceImplTest2 {

    @InjectMocks
    private CustomUserDetailServiceImpl userDetailService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsername() {
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(User.builder().username("anish").password("asdfasdfas").roles(new ArrayList<>()).build()));
        UserDetails userDetails = userDetailService.loadUserByUsername("anish");
        Assertions.assertNotNull(userDetails);
    }

}
