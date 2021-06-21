package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {





   Item getItemByItemname(String itemname);


    void deleteById(String itemname);

    boolean existsById(String itemname);
    boolean existsByItemname(String itemname);

    void deleteByItemname(String itemname);

}
