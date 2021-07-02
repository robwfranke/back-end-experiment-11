package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.AddJob;
import nl.lotrac.bv.controller.model.CreateCustomerWithAddress;
import nl.lotrac.bv.controller.model.CreateItem;
import nl.lotrac.bv.model.Item;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.service.CustomerService;
import nl.lotrac.bv.service.ItemService;
import nl.lotrac.bv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/customers")


@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;



    @PostMapping(value = "/createCustomerWithAddress")

    public ResponseEntity<Object> createCustomerWithAddress(@RequestBody CreateCustomerWithAddress createCustomerWithAddress) {

        User user = customerService.createCustomerWithAddress(createCustomerWithAddress);

//        hier adres op geven waar je customer of user kunt opvragen
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{createCustomerWithAddress}")
                .buildAndExpand(user.getUsername()).toUri();


        return ResponseEntity.created(location).body(user);
    }




}
