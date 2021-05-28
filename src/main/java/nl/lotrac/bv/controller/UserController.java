package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.model.MessageFrontEnd;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.exceptions.BadRequestException;
import nl.lotrac.bv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController


@RequestMapping(value = "/users")

@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @GetMapping(value = "/name/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        String newUsername = userService.createUser(user);
        userService.addAuthority(user.getUsername(), Role.COMPANY_USER);
        MessageFrontEnd messageFrontEnd = new MessageFrontEnd("User: " + newUsername + "  created");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

//        return ResponseEntity.created(location).build();
        return ResponseEntity.created(location).body(messageFrontEnd);
    }




    @GetMapping(value = "/authorities")
    public ResponseEntity<Object> getAllAuthorities() {
        return ResponseEntity.ok().body(userService.getAllAuthorities());
    }


    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, Role.valueOf(authorityName));
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, Role.valueOf(authority));
        return ResponseEntity.noContent().build();
    }

}
