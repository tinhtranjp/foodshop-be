package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.ProductDTO;
import com.example.foodshopbe.dtos.ProductImageDTO;
import com.example.foodshopbe.models.Product;
import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.respons.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws  Exception;
    public Page<ProductResponse> getAllProducts(String keyword,
                                                Long categoryId,
                                                Double minPrice,
                                                Double maxPrice,
                                                Boolean isFreeShip,
                                                Boolean isPromotion,
                                                PageRequest pageRequest);

    Product getProductById(int id) throws Exception;

    List<Product> get20productDESC();

    List<Product> getFullProducts();
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Product updateProduct(int id, ProductDTO productDTO) throws Exception;

    void deleteProduct(int id);

    ProductImage createProductImage(
            int productId,
            ProductImageDTO productImageDTO
    ) throws Exception;

    boolean existsByName(String name);
}