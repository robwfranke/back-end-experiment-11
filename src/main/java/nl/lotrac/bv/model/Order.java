package nl.lotrac.bv.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "orders")



public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(nullable = false, unique = true)
    private String ordername;

    @Column
    private String description;

    @Column(nullable = false, length = 255)
    private String status;




    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="fk_user")
    private User user;

    @OneToMany
    @JoinColumn(name="fk_order")
    private List<Item> items;

}

