
package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingDTO {
    private int incomingNum;
    private int count;
    private Date incomingDate;
    private String status;
    private String productId;
    private String userId;

    @Override
    public String toString() {
        return String.format("| %-7d | %-10s | %-10d | %-15s | %-7s | %-10s |",
                incomingNum, productId, count, incomingDate, status, userId);
    }
}
