package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nl.lotrac.bv.service.UserServiceImpl;
import nl.lotrac.bv.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)

@Slf4j
@RestController
@RequestMapping(value = "/admin")


public class
AdminController {


    @Autowired
    private UserService userService;






    @PutMapping(value = "/changeUser/{username}")
    public User updateUser(@PathVariable("username") String username, @RequestBody User user) {
        log.debug("Admincontroller");
        userService.updateUser(username,user);
        return userService.getUser(username);
    }


}
