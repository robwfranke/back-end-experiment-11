package nl.lotrac.bv.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString

public class FileResponse {

    private String id;
    private String name;
    private Long size;
    private String url;
    private String contentType;


}