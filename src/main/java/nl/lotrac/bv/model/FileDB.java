package nl.lotrac.bv.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;




@Getter
@Setter
@EqualsAndHashCode
@ToString


@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    private Long size;

    @Lob
    private byte[] data;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data,Long size) {
        this.name = name;
        this.type = type;
        this.size =size;
        this.data = data;

    }
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="fk_user")
    private User user;



}
