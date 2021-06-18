package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.utils.ExtractUserName;
import org.hibernate.mapping.IdentifierBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private ExtractUserName extractUserName;


    @Override
    public String createNewOrder(Order order) {
        log.debug("order", order);
        String username = extractUserName.extractUserNameFromJwt();
        log.debug("createNewOrder username", username);
        User user = userRepository.getUserByUsername(username);


//
//        boolean found = false;
//        for (Order o : user.getOrders()) {
//            if (o.getOrdername().equals(order.getOrdername())) {
//                found = true;
//                break;
//            }
//        }


        if (user.getOrders().stream().anyMatch(o -> order.getOrdername().equals(o.getOrdername())))
//        if (orderRepository.getOrderByOrdername(order.getOrdername()) != null)
            throw new NameExistsException("order exists");

//        jpa weet nu, via de foreign key dat hier username komt te staan

        order.setUser(user);
        order.setStatus("open");
        Order newOrder = orderRepository.save(order);
        return (newOrder.getOrdername());
    }


    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    @Override
    public List<Order> getAllOrdersByUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null)
            throw new NameNotFoundException("order not present");
        return user.getOrders();
    }


    @Override
    public Order getOneOrderByID(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NameNotFoundException("order does not exists");
        } else {
            return order.get();
        }
    }


    //    in repository getOrderByOrdername
    @Override
    public Order getOneOrderByName(String ordername) {
        System.out.println("OrderServiceImpl getOneOrderByName");
        Order order = orderRepository.getOrderByOrdername(ordername);
        System.out.println("order1");
        if (order == null)
            throw new NameNotFoundException("order does not exists");
        return order;
    }

//    public abstract void updateUser(String username, User user);


    @Override
    public void updateOrder(String ordername, Order updateOrder) {


        String username = extractUserName.extractUserNameFromJwt();

        User user = userRepository.getUserByUsername(username);


        log.debug("ordername:  " + ordername);


        log.debug("update order: " + updateOrder);
        log.debug("username: " + username);

        log.debug("user: " + user.getUsername());


        log.debug("updateOrder.getOrdername(): " + updateOrder.getOrdername());
        log.debug("updateOrder.getgetStatus(): "+updateOrder.getStatus());


        for (Order o : user.getOrders()) {
            log.debug(o.getOrdername());
        }


//        log.debug("orders in update order voor rob", user.getOrders().size());

        Order orderFound = new Order();

        boolean found = false;
        for (Order loop : user.getOrders()) {
            if (loop.getOrdername().equals(updateOrder.getOrdername())) {
                orderFound=loop;
                found = true;
                break;
            }
        }
        log.debug("found" + found);


//        Order order = orderRepository.getOrderByOrdername(ordername);

        if (found == false)
            throw new NameNotFoundException("order does not exists");

//
        orderFound.setStatus(updateOrder.getStatus());
//
        orderRepository.save(orderFound);

    }


}
