package model.service;

import common.constants.ErrorCode;
import common.constants.MessageEnum;
import model.dao.ProductDAO;
import model.dao.ProductDAOImpl;
import model.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void registerProduct(ProductDTO productDTO) {
        if(!productDAO.isUserAuthorized(productDTO.getUserId())) {
            System.out.println(ErrorCode.USER_UNAUTHORIZED.getMessage());
            return;
        }
        productDAO.insertProduct(productDTO);
        System.out.println(MessageEnum.PRODUCT_REGISTER_SUCCESS.getMessage());
    }
}
