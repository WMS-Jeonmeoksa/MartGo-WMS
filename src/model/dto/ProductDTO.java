package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductDTO {
    private String productId;
    private String productName;
    private String category;
    private int height;
    private int width;
    private int price;
    private String manufacturer;
    private String userId;

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-12s | %6d | %6d | %,10d | %-12s | %-10s |",
                productId, productName, category, height, width, price,
                manufacturer == null ? "N/A" : manufacturer, userId);
    }
}
