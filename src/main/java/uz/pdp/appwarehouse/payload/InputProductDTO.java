package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.util.Date;
@Data
public class InputProductDTO {

    private Double amount;
    private Double price;
    private Date expireDate;
    private Integer productId;
    private Integer inputId;
}
