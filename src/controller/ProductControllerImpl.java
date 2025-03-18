package controller;

import model.dto.ProductDTO;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import javax.swing.plaf.ListUI;
import java.util.Scanner;

import static common.constants.MessageEnum.*;

public class ProductControllerImpl implements ProductController {
    ProductService productService = new ProductServiceImpl();
    Scanner sc = new Scanner(System.in);

    @Override
    public void registerProduct(String userId) {
        System.out.println(INPUT_PRODUCT_TITLE.getMessage());
        System.out.println(INPUT_PRODUCT_ID.getMessage());
        String productId = sc.nextLine();
        System.out.println(INPUT_PRODUCT_NAME.getMessage());
        String productName = sc.nextLine();
        System.out.println(INPUT_PRODUCT_CATEGORY.getMessage());
        String category = sc.nextLine();
        System.out.println(INPUT_PRODUCT_HEIGHT.getMessage());
        int height = Integer.parseInt(sc.nextLine());
        System.out.println(INPUT_PRODUCT_WIDTH.getMessage());
        int width = Integer.parseInt(sc.nextLine());
        System.out.println(INPUT_PRODUCT_PRICE.getMessage());
        int price = Integer.parseInt(sc.nextLine());
        System.out.println(INPUT_PRODUCT_MANUFACTURER.getMessage());
        String manufacturer = sc.nextLine();

        ProductDTO productDTO = ProductDTO.builder()
                .productId(productId)
                .productName(productName)
                .category(category)
                .height(height)
                .width(width)
                .price(price)
                .manufacturer(manufacturer)
                .userId(userId).build();

        productService.registerProduct(productDTO);
    }
}
