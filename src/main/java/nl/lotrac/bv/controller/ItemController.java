package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.AddJob;
import nl.lotrac.bv.controller.model.CreateItem;
import nl.lotrac.bv.model.Item;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/items")


@Slf4j
public class ItemController {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/create")

    public ResponseEntity<Item> createNewItem(@RequestBody CreateItem createItem) {
        log.debug(createItem.toString());
        Item item = itemService.createNewItem(createItem);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(item.getItemname()).toUri();

        return ResponseEntity.created(location).body(item);
    }


    @GetMapping(value = "")
    public ResponseEntity<Object> getAllItems() {

        return ResponseEntity.ok().body(itemService.getAllItems());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getOneItemByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(itemService.getOneItemByID(id), HttpStatus.OK);
    }



    @GetMapping(value = "/name/{itemname}")
    public ResponseEntity<Object> getItemByName(@PathVariable("itemname") String itemname) {
        return ResponseEntity.ok().body(itemService.getOneItemByName(itemname));
    }

    @PostMapping(value = "/addJob")
    public ResponseEntity<Item>addJob(@RequestBody AddJob addJob){
        log.debug(addJob.getItemName());
       log.debug(addJob.getJobName());
       log.debug(addJob.getDepartment());

       return ResponseEntity.ok().body(itemService.addJob(addJob));
    }

    @DeleteMapping(value="/delete/job/{itemname,jobname}")
    public ResponseEntity<Item>deleteJobByName(@PathVariable("jobname") String itemname,String jobname){

        itemService.deleteJobByName(itemname,jobname);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") long id) {
       itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/delete/itemname/{itemname}")
    public ResponseEntity<Object> deleteItemByName(@PathVariable("itemname") String itemname) {
        itemService.deleteItemByName(itemname);
        return ResponseEntity.noContent().build();
    }



}
