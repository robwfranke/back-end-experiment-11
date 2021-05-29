package nl.lotrac.bv.service;

import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.AddressRepository;
import nl.lotrac.bv.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)



class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;


    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void userExists() {
//        arrange
        String username="piet";

        when(userRepository.existsById("piet")).thenReturn(true);


//        act

        boolean result = userService.userExists(username);

        assertThat(result).isTrue();

//        verify hier test je daadwerkelijk wat echt aangeroepen is.


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository,passwordEncoder,addressRepository);

    }

    @Test
    void userDoesNotExists() {
//        arrange
        String username="piet";

        when(userRepository.existsById("piet")).thenReturn(false);


//        act

        boolean result = userService.userExists(username);

        assertThat(result).isFalse();

//        verify hier test je daadwerkelijk wat echt aangeroepen is.


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository,passwordEncoder,addressRepository);

    }




    @Test
    void createUserExists() {

        User testUser=mock(User.class);
        String username="piet";
        when(testUser.getUsername()).thenReturn(username);
        when(userRepository.existsById(username)).thenReturn(true);

        try {

            userService.createUser(testUser);

        }catch(NameExistsException ex){
            assertThat(ex).hasMessageContaining(username);
        }


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository,passwordEncoder,addressRepository);


    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void getAllAuthorities() {
    }

    @Test
    void getAuthorities() {
    }

    @Test
    void addAuthority() {
    }

    @Test
    void removeAuthority() {
    }
}