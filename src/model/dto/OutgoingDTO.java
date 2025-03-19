package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutgoingDTO {
    private int outgoingNum;
    private int count;
    private Date outgoingDate;
    private String status;
    private String userId;
    private int stockNum;
}
