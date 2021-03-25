package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private Boolean active;
    private Integer categoryId;
    private Integer attachmentId;
    private Integer measurementId;
    private String code;
}
