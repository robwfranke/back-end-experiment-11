package nl.lotrac.bv.controller.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString


public class
CreateItem {
    //data order
    private String orderName;
    //data item

    private String itemName;

    private Integer quantity;

}
