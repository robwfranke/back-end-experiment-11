package nl.lotrac.bv.service;

import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@TestConfiguration
@RunWith(SpringRunner.class)

class UserServiceImplIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration{

        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;




@Before
    public void setUp(){
        User userTest =new User("testname");

//        userTest.setUsername("testname");

        Mockito
                .when(userRepository.getUserByUsername(userTest.getUsername()))
                .thenReturn(userTest);

    }

    @Test
    public void testGetUserByUsername(){
    String name = "testname";

    User found = userService.getUserByUsername(name);

        assertThat(found.getUsername()).isEqualTo(name);
    }



}