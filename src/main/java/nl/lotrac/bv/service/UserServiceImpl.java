package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.exceptions.*;
import nl.lotrac.bv.model.*;

import nl.lotrac.bv.repository.AddressRepository;
import nl.lotrac.bv.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }


    @Override
    public User createUser(User user) {
        if (userRepository.existsById(user.getUsername()))
            throw new NameExistsException(user.getUsername() + "  exists!!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return (newUser);
    }


    @Override
    public User updateUser(String username, User newUser) {

        User user = userRepository.findById(username).orElseThrow(() -> new NameNotFoundException("user does not exist"));
//        laat return type zien
//        Optional<User> byId = userRepository.findById(username);


        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser1 = userRepository.save(user);
        return (newUser1);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }


    @Override
    public Collection<User> getUsers() {


        return userRepository.findAll();
    }


    @Override
    public Collection<User> getAllAuthorities() {
        return userRepository.findAll();
    }


    @Override
    public Set<Authority> getAuthorities(String username) {
        User user = userRepository.findById(username).orElseThrow(() -> new NameNotFoundException("user does not exist"));

//        if (!userRepository.existsById(username)) throw new NameNotFoundException(username);
//        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    @Override
    public void addAuthority(String username, Role authority) {
        User user = userRepository.findById(username).orElseThrow(() -> new NameNotFoundException("user does not exist"));

//        if (!userRepository.existsById(username)) throw new NameNotFoundException(username);
//        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    @Override
    public void removeAuthority(String username, Role authority) {
        if (!userRepository.existsById(username)) throw new NameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthorityRole().equals(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

}