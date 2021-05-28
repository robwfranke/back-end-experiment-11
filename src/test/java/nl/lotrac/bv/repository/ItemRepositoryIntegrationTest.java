package nl.lotrac.bv.repository;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j

public class ItemRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenGetItemByItemnameThenReturnItem() {
log.debug("start test");
//        given

        Item item = new Item();
          System.out.println("testName: "+item.getItemname());
        entityManager.persist(item);
        entityManager.flush();

//       when

        Item found = itemRepository.getItemByItemname(item.getItemname());

//        then

        assertThat(found.getItemname()).isEqualTo(item.getItemname());


    }


}