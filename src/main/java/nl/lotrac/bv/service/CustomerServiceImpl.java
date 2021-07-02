package nl.lotrac.bv.service;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CreateCustomerWithAddress;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.model.Address;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private CustomerService customerService;


    @Override
    public User createCustomerWithAddress(CreateCustomerWithAddress createCustomerWithAddress) {

        log.debug("CustomerServiceImpl createCustomerWithAddress " + createCustomerWithAddress.toString());


        if (userRepository.existsById(createCustomerWithAddress.getUsername()))
            throw new NameExistsException(createCustomerWithAddress.getUsername() + "  exists!!");


        User newUser = new User();
        Address newAddress = new Address();

        newUser.setUsername(createCustomerWithAddress.getUsername());
        newUser.setPassword(passwordEncoder.encode(createCustomerWithAddress.getPassword()));
        newUser.setEnabled(true);

        newAddress.setStreet(createCustomerWithAddress.getStreet());
        newAddress.setCity(createCustomerWithAddress.getCity());
        newAddress.setPostalcode(createCustomerWithAddress.getPostalcode());
        newAddress.setTelnumber(createCustomerWithAddress.getTelnumber());


//  Methode 1:  hier 2x toegang tot database en de newUser met adres wordt teruggegeven

        addressRepository.save(newAddress);
//        hier wordt de foreignkey geset
        newUser.setAddresses(List.of(newAddress));

        userRepository.save(newUser);
        userService.addAuthority(newUser.getUsername(), Role.CUSTOMER);
        return (newUser);


//// Methode 2: hier 3x toegang tot database en de newUser wordt zonder address teruggegeven
//        addressRepository.save(newAddress);
//        userRepository.save(newUser);
//        userService.addAuthority(newUser.getUsername(), Role.CUSTOMER);
//         hier wordt de foreignkey geset
//        newAddress.setUser(newUser);
//        userRepository.save(newUser);
//        return (newUser);


    }


}
