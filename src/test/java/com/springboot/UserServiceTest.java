package com.springboot;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void testUserService() {
        Assertions.assertEquals(4, 2+2);
        Assertions.assertNotNull(userService.findByUsername("ram"));
        Assertions.assertTrue(userService.findByUsername("ram").isPresent());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public void randomCsvTest(int a, int b, int c) {
        Assertions.assertEquals(c, a + b);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "anish",
            "ram",
            "shyam"
    })
    public void testUsersByCsvSource(String name) {
        Assertions.assertTrue(userService.findByUsername(name).isPresent(), " of user: " + name);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "anish",
            "ram",
            "shyam"
    })
    public void testUsersByValueSource(String name) {
        Assertions.assertTrue(userService.findByUsername(name).isPresent(), " of user: " + name);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testUsersByCustomSource(User user) {
        Assertions.assertTrue(userService.testSave(user));
    }

    // We can use these test annotation as well when needed
    /*@BeforeEach
    @BeforeAll
    @AfterAll
    @AfterEach*/
    public void test() {}

}