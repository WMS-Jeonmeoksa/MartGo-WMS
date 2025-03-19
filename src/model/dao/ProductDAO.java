package model.dao;

import model.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {
    void insertProduct(ProductDTO product);
    boolean isUserAuthorized(String userId);
    List<ProductDTO> getProductByUserId(String userId);
}
