package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDTO {

    private Timestamp date;
    private String factureNumber;
    private String code;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;

}
