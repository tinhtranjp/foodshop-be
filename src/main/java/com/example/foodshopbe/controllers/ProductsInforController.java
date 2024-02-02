package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.ProductsInforDTO;
import com.example.foodshopbe.models.ProductInfor;
import com.example.foodshopbe.services.Imp.IProductInforService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products-infor")
@RequiredArgsConstructor
public class ProductsInforController {

    private final IProductInforService IProductInforService;

    @GetMapping("") //http://localhost:8088/api/v1/products-infor
    public ResponseEntity<List<ProductInfor>> getAllProductsInfor() {
        List<ProductInfor> productInforList = IProductInforService.getAllProductInfor();
        return  ResponseEntity.ok(productInforList);
    }

    @PostMapping("")
    public ResponseEntity<?> insertProductsInfor(@Validated @RequestBody ProductsInforDTO productsInforDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages =
                    result.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        ProductInfor newProductInfor = IProductInforService.createProductInfor(productsInforDTO);
        return  ResponseEntity.ok(newProductInfor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductsInfor(
            @Validated @PathVariable int id,
            @RequestBody ProductsInforDTO productsInforDTO,
            BindingResult result) {

        if(result.hasErrors()) {
            List<String> errorMessages =
                    result.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        ProductInfor newProductInfor;
        try {
             newProductInfor = IProductInforService.updateProductInfor(id, productsInforDTO);
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
        return  ResponseEntity.ok(newProductInfor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductsInfor(@PathVariable int id) {
        return  ResponseEntity.ok("this is delete product infor id: " + id);
    }
}
