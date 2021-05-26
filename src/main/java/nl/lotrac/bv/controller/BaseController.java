package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CreateCustomerWithAddress;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nl.lotrac.bv.service.CustomerService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@Slf4j
public class BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;


//    new endpoint om user te maken

    @PostMapping(value = "/createCustomerWithAddress")

    public ResponseEntity<Object> createCustomerWithAddress(@RequestBody CreateCustomerWithAddress createCustomerWithAddress) {

        User user = customerService.createCustomerWithAddress(createCustomerWithAddress);

//        hier adres op geven waar je customer of user kunt opvragen
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{createCustomerWithAddress}")
                .buildAndExpand(user.getUsername()).toUri();


        return ResponseEntity.created(location).body(user);
    }




}
