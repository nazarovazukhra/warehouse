package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class OutputProductDTO {

    private Double amount;
    private Double price;
    private Integer productId;
    private Integer outputId;
}
