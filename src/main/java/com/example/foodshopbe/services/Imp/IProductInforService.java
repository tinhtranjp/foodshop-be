package com.example.foodshopbe.services.Imp;

import com.example.foodshopbe.dtos.ProductsInforDTO;
import com.example.foodshopbe.models.ProductInfor;

import java.util.List;

public interface IProductInforService {
    ProductInfor createProductInfor(ProductsInforDTO productsInforDTO);

    ProductInfor getProductInforById(int id) throws Exception;

    List<ProductInfor> getAllProductInfor();
    ProductInfor updateProductInfor(int id, ProductsInforDTO productsInforDTO) throws Exception;

    void deleteProductInfor(int id);
}
