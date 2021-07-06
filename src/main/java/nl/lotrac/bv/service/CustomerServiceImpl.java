package nl.lotrac.bv.service;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CustomerWithAddress;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.model.Address;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.repository.AddressRepository;

import nl.lotrac.bv.utils.ExtractUserName;
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
    private ExtractUserName extractUserName;


    @Autowired
    private CustomerService customerService;

    @Override
    public void updateDataCustomer(CustomerWithAddress customerWithAddress) {


        log.debug("IN updateDataCustomer");

        String username = extractUserName.extractUserNameFromJwt();
        User user = userRepository.getUserByUsername(username);
        log.debug("*****************");
        log.debug("username: " + username);
        log.debug("existing email: " + user.getEmail());
        log.debug("existing password: " + user.getPassword());

        log.debug("*****************");

        log.debug("updateDataCustomer,password: " + customerWithAddress.getPassword());
        log.debug("updateDataCustomer,email: " + customerWithAddress.getEmail());
        log.debug("updateDataCustomer,city: " + customerWithAddress.getCity());

        log.debug("updateDataCustomer,street: " + customerWithAddress.getStreet());
        log.debug("updateDataCustomer,postalcode: " + customerWithAddress.getPostalcode());
        log.debug("updateDataCustomer,telnumber: " + customerWithAddress.getTelnumber());

        log.debug("*****************");


//        haal adres op dat bij user hoort

        Address existingAddress = addressRepository.getAddressByUser(user);


        log.debug("existingAddress city: " + existingAddress.getCity());
        log.debug("existingAddress street: " + existingAddress.getStreet());
        log.debug("existingAddress postalcode: " + existingAddress.getPostalcode());
        log.debug("existingAddress telnumber: " + existingAddress.getTelnumber());

//        if(customerWithAddress.getEmail()!=null ||
//                customerWithAddress.getCity()!=null||
//                customerWithAddress.getStreet()!=null||
//                customerWithAddress.getPostalcode()!=null||
//                customerWithAddress.getTelnumber()!=null
//
//        ){

            log.debug("VERANDER ADDRESS");

            if(customerWithAddress.getCity()!=null){
                log.debug("Update City");
            existingAddress.setCity(customerWithAddress.getCity());
            }

        if(customerWithAddress.getStreet()!=null) {
            log.debug("Update Street");
            existingAddress.setStreet(customerWithAddress.getStreet());
        }

        if(customerWithAddress.getPostalcode()!=null) {
            log.debug("Update PC");
            existingAddress.setPostalcode(customerWithAddress.getPostalcode());
        }
        if(customerWithAddress.getTelnumber()!=null) {
            log.debug("Update Tel");
            existingAddress.setTelnumber(customerWithAddress.getTelnumber());
        }
            addressRepository.save(existingAddress);

//        }

//
//if(customerWithAddress.getPassword().length()>=8) {
//    log.debug("IN PASSWORDCHANGE");
//    user.setPassword(passwordEncoder.encode(customerWithAddress.getPassword()));
//
//}


log.debug("Updaten email"+customerWithAddress.getEmail());
        user.setEmail(customerWithAddress.getEmail());
//        user.setEmail("pipo");

        userRepository.save(user);



    }


    @Override
    public User createCustomerWithAddress(CustomerWithAddress customerWithAddress) {

        log.debug("CustomerServiceImpl customerWithAddress " + customerWithAddress.toString());


        if (userRepository.existsById(customerWithAddress.getUsername()))
            throw new NameExistsException(customerWithAddress.getUsername() + "  exists!!");


        User newUser = new User();
        Address newAddress = new Address();

        newUser.setUsername(customerWithAddress.getUsername());
        newUser.setPassword(passwordEncoder.encode(customerWithAddress.getPassword()));
        newUser.setEnabled(true);

        newAddress.setStreet(customerWithAddress.getStreet());
        newAddress.setCity(customerWithAddress.getCity());
        newAddress.setPostalcode(customerWithAddress.getPostalcode());
        newAddress.setTelnumber(customerWithAddress.getTelnumber());


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
