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

    @Override
    public String toString() {
        return String.format("| %-7d | %-6d | %-12s | %-6s | %-7s | %-7d |",
                outgoingNum, count, outgoingDate, status, userId, stockNum);
    }
}
