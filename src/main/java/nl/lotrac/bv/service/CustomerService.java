package nl.lotrac.bv.service;

import nl.lotrac.bv.controller.model.CustomerWithAddress;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.User;


public interface CustomerService {

    public abstract User createCustomerWithAddress(CustomerWithAddress customerWithAddress);




    void updateDataCustomer(CustomerWithAddress customerWithAddress);

}