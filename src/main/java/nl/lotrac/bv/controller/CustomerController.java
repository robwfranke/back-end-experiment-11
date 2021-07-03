package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CustomerWithAddress;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.service.CustomerService;
import nl.lotrac.bv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/customers")


@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;


    @PostMapping(value = "")

    public ResponseEntity<Object> createCustomerWithAddress(@RequestBody CustomerWithAddress customerWithAddress) {

        User user = customerService.createCustomerWithAddress(customerWithAddress);

//        hier adres op geven waar je customer of user kunt opvragen
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
                .buildAndExpand(user.getUsername()).toUri();


        return ResponseEntity.created(location).body(user);
    }

    @PutMapping(value = "")
    public ResponseEntity<Object> updateDataCustomer(@Valid @RequestBody  CustomerWithAddress customerWithAddress) {
        log.debug("CustomerController");
        customerService.updateDataCustomer(customerWithAddress);
        return ResponseEntity.noContent().build();
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.debug("errors in @ExceptionHandler "+errors);
        return errors;
    }


}
