package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository <Order, Long >{

List<Order> getOrdersByUser(User user);

    Order getOrderByOrdername(String orderName);
}
