package nl.lotrac.bv.service;

import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.AddressRepository;
import nl.lotrac.bv.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Optional;
import java.util.AbstractList;


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
        String username = "piet";
//mock, definieer alle afhankelijkheden
        when(userRepository.existsById("piet")).thenReturn(true);

//        act

        boolean result = userService.userExists(username);

//        assert
        assertThat(result).isTrue();

//        verify hier test je daadwerkelijk wat echt aangeroepen is.


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);
    }

    @Test
    void userDoesNotExists() {
//        arrange
        String username = "piet";

        when(userRepository.existsById("piet")).thenReturn(false);

//        act

        boolean result = userService.userExists(username);

        assertThat(result).isFalse();

//        verify hier test je daadwerkelijk wat echt aangeroepen is.


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);


    }


    @Test
    void createUserExists() {


        User testUser = mock(User.class);
        String username = "piet";

        when(testUser.getUsername()).thenReturn(username);/*(userRepository.existsById(user.getUsername()))*/
        when(userRepository.existsById(username)).thenReturn(true);


        try {

            userService.createUser(testUser);

        } catch (NameExistsException ex) {
            assertThat(ex).hasMessageContaining(username);
        }


        verify(userRepository).existsById(username);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);
    }


    @Test
    void createUserDoesNotExists() {

//arrange
        User testUser = mock(User.class);
        User newUser = mock(User.class);


        String username = "piet";
        String passwordReturn = "qrt";
        String encodedPassword = "hcxh";


//mock, definieer alle afhankelijkheden

        when(testUser.getUsername()).thenReturn(username);
        when(userRepository.existsById(username)).thenReturn(false);

        when(testUser.getPassword()).thenReturn(passwordReturn);
        when(passwordEncoder.encode(passwordReturn)).thenReturn(encodedPassword);
        when(userRepository.save(testUser)).thenReturn(newUser);


//        act
        User result = userService.createUser(testUser);


        assertThat(result).isEqualTo(newUser);


        verify(userRepository).existsById(username);
        verify(passwordEncoder).encode(passwordReturn);
        verify(userRepository).save(testUser);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);
    }


    @Test
    void updateUserDoesNotExist() {

//        Arrange

        User testUser = mock(User.class);
        String username = "piet";

        when(userRepository.findById(username)).thenReturn(Optional.empty());
        Optional.of("gg").orElse("jdjd");

//        act
        try {
            userService.updateUser(username, testUser);

        } catch (NameNotFoundException ex) {

//            assert
            assertThat(ex).hasMessage("user does not exist");
        }
    }


    @Test
    void updateUserExist() {

//        Arrange

        User testUser = mock(User.class);
        User newUser = mock(User.class);


        String username = "piet";
        String passwordReturn = "qrt";
        String encodedPassword = "hcxh";


//mock, definieer alle afhankelijkheden


        when(userRepository.findById(username)).thenReturn(Optional.of(testUser));

        when(testUser.getPassword()).thenReturn(passwordReturn);
        when(passwordEncoder.encode(passwordReturn)).thenReturn(encodedPassword);

        when(userRepository.save(testUser)).thenReturn(newUser);
//        deze newUser is dan het gedeelte van isEqualTo


//        act
//        userService.updateUser(username, testUser);

        User result = userService.updateUser(username, testUser);
//
        assertThat(result).isEqualTo(newUser);


        verify(passwordEncoder).encode(passwordReturn);
        verify(userRepository).save(testUser);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);


    }


    @Test
    void deleteUser() {

        String username = "piet";
//hier kun je alleen methode teste, geen mock nodig


//        act

        userService.deleteUser(username);


//        verify hier test je daadwerkelijk wat echt aangeroepen is.
        verify(userRepository).deleteById(username);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);
    }


    @Test
    void getUserByUsername() {
// Arrange
        User testUser = mock(User.class);
        String username = "piet";

        when(userRepository.getUserByUsername(username)).thenReturn(testUser);

//        Act
        userService.getUserByUsername(username);


//        verify hier test je daadwerkelijk wat echt aangeroepen is.
        verify(userRepository).getUserByUsername(username);
        verifyNoMoreInteractions(userRepository, passwordEncoder, addressRepository);


    }

    @Test
    void getUsers() {
//        Collection mockCollection = mock(Collection.class);
//        User user1=mock(User.class);
//        User user2=mock(User.class);
//        User user3=mock(User.class);
//
//        mockCollection.add(user1);
//        mockCollection.add(user2);
//        mockCollection.add(user3);
//
////        userService.getUsers();
//
//assertThat(mockCollection).hasSize(3);

        userService.getUsers();



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