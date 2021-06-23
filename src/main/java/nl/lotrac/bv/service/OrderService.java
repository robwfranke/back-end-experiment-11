package nl.lotrac.bv.service;

import nl.lotrac.bv.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    String createNewOrder(Order order);


    public abstract List<Order> getAllOrders();

    public abstract List<Order> getAllOrdersByUser(String username);


    public abstract Order getOneOrderByID(Long id);

    //    in repository getOrderByOrdername
    public abstract Order getOneOrderByName(String order);


    public abstract void updateOrder(String ordername, Order order);

    public abstract void deleteOrderByName(String ordername);



}
