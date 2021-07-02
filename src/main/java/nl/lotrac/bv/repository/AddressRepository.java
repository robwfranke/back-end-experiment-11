package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.Address;
import nl.lotrac.bv.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long >{


    Address getAddressByUser(User user);





}
