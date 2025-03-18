package model.dao;

import model.dto.ProductDTO;

public interface ProductDAO {
    void insertProduct(ProductDTO product);
    boolean isUserAuthorized(String userId);
}
