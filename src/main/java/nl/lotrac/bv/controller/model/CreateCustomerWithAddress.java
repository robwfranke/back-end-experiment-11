package nl.lotrac.bv.controller.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;


@Getter
@Setter
@EqualsAndHashCode
@ToString



public class
CreateCustomerWithAddress {

//    user details

    private String username;

    private String password;

    private boolean enabled = true;

    private String email;


//    address details


    private String street;


    private String city;


    private String postalcode;


    private String telnumber;



}
