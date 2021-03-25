package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class CategoryDTO {

    private String name;
    private Boolean active;
    private Integer parentCategoryId;
}
