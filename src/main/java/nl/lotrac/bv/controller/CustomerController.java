package nl.lotrac.bv.controller;

import nl.lotrac.bv.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// localhost 3000;
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping(value = "/customers")

public class CustomerController {

    @Autowired
    private CustomerService customerService;


}
