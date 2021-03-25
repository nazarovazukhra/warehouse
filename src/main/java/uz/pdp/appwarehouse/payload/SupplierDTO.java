package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class SupplierDTO {

    private String name;
    private Boolean active;
    private String phoneNumber;

}
