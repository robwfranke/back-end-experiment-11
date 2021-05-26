package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.utils.ExtractUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createNewOrder(Order order) {

        String username = ExtractUserName.ExtractUserNameFromJwt();
        User user = userRepository.getUserByUsername(username);


        if (orderRepository.getOrderByOrdername(order.getOrdername()) != null)
            throw new NameExistsException("order exists");

//        jpa weet nu, via de foreign key dat hier username komt te staan

        order.setUser(user);
        order.setStatus("pending");
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
    public void updateOrder(String ordername, Order newOrder) {
        log.debug("update order: " + newOrder);
        System.out.println("ordername");
        Order order = orderRepository.getOrderByOrdername(ordername);

        if (order == null)
            throw new NameNotFoundException("order does not exists");


        order.setStatus(newOrder.getStatus());

        orderRepository.save(order);


    }


}
