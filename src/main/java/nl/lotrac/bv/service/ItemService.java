package nl.lotrac.bv.service;

import nl.lotrac.bv.controller.model.AddJob;
import nl.lotrac.bv.controller.model.CreateItem;
import nl.lotrac.bv.model.Item;

import java.util.List;

public interface ItemService {

    Item createNewItem(CreateItem item);

    public abstract List<Item> getAllItems();

    public abstract Item getOneItemByID(Long id);

    public abstract Item addJob(AddJob addJob);

    //    In repository staat getItemByItemName
    public abstract Item getOneItemByName(String itemname);

    public abstract void deleteItem(long id);

    public abstract void deleteItem1(String itemname);


}
