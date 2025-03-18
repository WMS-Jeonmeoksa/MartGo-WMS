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
}
