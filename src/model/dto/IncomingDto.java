package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingDTO {
    private int incomingNum;
    private int count;
    private Date incomingDate;
    private String status;
    private String productId;
    private int userId;
}
