package nl.lotrac.bv.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;


    @Column(length = 255)
    private String street;

   @NotBlank(message = "Mag niet leeg zijn")
    private String city;

    @Column(length = 255)
    private String postalcode;

    @Column(length = 255)
    private String telnumber;

@JsonIgnore
    @ManyToOne
    @JoinColumn(name="fk1_user")
private User user;



}