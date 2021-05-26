package nl.lotrac.bv.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@ToString




@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Role authorityRole;

    public Authority() {}
    public Authority(String username, Role authority) {
        this.username = username;
        this.authorityRole = authority;
    }

}
