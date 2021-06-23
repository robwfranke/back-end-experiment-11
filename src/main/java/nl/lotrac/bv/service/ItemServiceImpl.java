package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.AddJob;
import nl.lotrac.bv.controller.model.CreateItem;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Job;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.Item;
import nl.lotrac.bv.repository.JobRepository;
import nl.lotrac.bv.repository.ItemRepository;
import nl.lotrac.bv.repository.OrderRepository;

import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.utils.ExtractUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private ExtractUserName extractUserName;


    @Override
    public Item addJob(AddJob addJob) {

        Item item = itemRepository.getItemByItemname(addJob.getItemName());
        Job job = jobRepository.getJobByJobname(addJob.getJobName());


//check of item bestaat
        if (item == null) {
            throw new NameNotFoundException("Item does not exists");
        }
//check of job bestaat

        if (job == null) {
            throw new NameNotFoundException("job does not exists");
        }

        if (item.getJobsFromItem() == null) {
            List<Job> jobs = List.of(job);
            item.setJobsFromItem(jobs);
        } else if (item.getJobsFromItem().contains(job)) {

            throw new NameNotFoundException("job does exists");

        } else {

            item.getJobsFromItem().add(job);
        }


        return itemRepository.save(item);

    }


    @Override
    public Item createNewItem(CreateItem createItem) {
        log.debug(createItem.toString());

        Order order = orderRepository.getOrderByOrdername(createItem.getOrderName());

//        check of deze order bestaat
        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);


        String username = extractUserName.extractUserNameFromJwt();
        log.debug("!!! username:  " + username);


        String ordername1 = order.getOrdername();
        log.debug("!!! ordername: " + ordername1);

// via de methode order.getUser kun je met getUsername() haal je de username op die bij deze order hoort.
//        check of deze order bij de user hoort
        if (!order.getUser().getUsername().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

//        check of Item bij deze user bestaat

        if (itemRepository.getItemByItemname(createItem.getItemName()) != null)
            throw new NameExistsException("Item exists");


//    order heeft de velden:
//      private long id;
//      private String ordername;
//      private String status;
//
//     hier is dus een copy uit de repositoryOrder gemaakt mbv de createItem, CreateItem createItem
//


        log.debug("order Id: " + String.valueOf(order.getId()));
        log.debug("ordername: " + order.getOrdername());
        log.debug("orderStatus: " + order.getStatus());

        Item newItem = new Item();


        newItem.setItemname(createItem.getItemName());
        newItem.setQuantity(createItem.getQuantity());
        log.debug("newOrderline ItemName: " + newItem.getItemname());
        log.debug("newOrderline Quantity: " + newItem.getQuantity());


//  wanneer je nu de order teruggeeft dmv de setter weet JPA via @ManyToOne dat hij dat in de foreign Key moet zetten
        newItem.setOrder(order);


// pas na save wordt een nieuwe id aangemaakt
        return itemRepository.save(newItem);
    }


    @Override
    public List<Item> getAllItems() {

        return itemRepository.findAll();
    }

    @Override
    public Item getOneItemByID(Long id) {

        Optional<Item> Item = itemRepository.findById(id);
        if (Item.isEmpty()) {
            throw new NameNotFoundException("Item does not exists");
        } else {
            return Item.get();
        }
    }


    @Override
    public Item getOneItemByName(String itemname) {

        Item item = itemRepository.getItemByItemname(itemname);
        if (item == null)
            throw new NameNotFoundException("item does not exists");

        return item;
    }


    @Override
    public void deleteItem(long id) {
        log.debug("id:", id);
        if (!itemRepository.existsById(id)) throw new NameNotFoundException("pipo");
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteItemByName(String itemname) {
        String username = extractUserName.extractUserNameFromJwt();
        log.debug("!!! username:  " + username);

        log.debug("itemname:  " + itemname);
       if(userService.getUserByUsername(username).getOrders().stream()
                .anyMatch(order->order.getItems().stream().anyMatch(item->item.getItemname().equals(itemname)))){
           itemRepository.deleteByItemname(itemname);

       }



    }


}
