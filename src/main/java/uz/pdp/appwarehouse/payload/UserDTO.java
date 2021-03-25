package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.Set;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Boolean active;
    private Set<Warehouse> warehouseSet;
}
