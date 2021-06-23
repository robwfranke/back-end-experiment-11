package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.service.OrderService;
import nl.lotrac.bv.utils.ExtractUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orders")


@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExtractUserName extractUserName;




    //********************************************************************************
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewOrder(@RequestBody Order order) {

        log.debug("COntroller debug");

        String newOrderName = orderService.createNewOrder(order);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(newOrderName).toUri();
        return ResponseEntity.created(location).body(order);
    }
//********************************************************************************


    //********************************************************************************
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
//********************************************************************************


    //********************************************************************************
    @GetMapping(value = "/ordersByUser/{username}")
    public ResponseEntity<Object> getAllordersByUser(@PathVariable("username") String username) {
        List<Order> orders = orderService.getAllOrdersByUser(username);
        return ResponseEntity.ok().body(orders);
    }
//********************************************************************************


    //********************************************************************************
    @GetMapping(value = "/inlog")
    public ResponseEntity<Object> getAllOrdersByInlogNameOnly() {
        String user = extractUserName.extractUserNameFromJwt();
        List<Order> orders = orderService.getAllOrdersByUser(user);
        return ResponseEntity.ok().body(orders);
    }
//********************************************************************************


    //********************************************************************************
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOneOrderByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getOneOrderByID(id), HttpStatus.OK);
    }
//********************************************************************************


    //********************************************************************************
    @GetMapping(value = "/name/{ordername}")
    public ResponseEntity<Object> getOneOrderByName(@PathVariable("ordername") String ordername) {
        return ResponseEntity.ok().body(orderService.getOneOrderByName(ordername));
    }
//********************************************************************************


    //********************************************************************************
    @PutMapping(value = "/update/{ordername}")

    public ResponseEntity<Object> updateOrder(@PathVariable("ordername") String ordername, @RequestBody Order order) {
        log.debug("Ordercontroller");
        orderService.updateOrder(ordername, order);
        return ResponseEntity.noContent().build();
    }
//********************************************************************************

@DeleteMapping(value= "/delete/ordername/{ordername}")
    public ResponseEntity<Object>deleteOrderByName(@PathVariable("ordername")String ordername){
      orderService.deleteOrderByName(ordername);
    return ResponseEntity.noContent().build();
}


}