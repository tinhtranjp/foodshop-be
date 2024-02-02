package com.example.foodshopbe.controllers;

import com.example.foodshopbe.dtos.ProductDTO;
import com.example.foodshopbe.dtos.ProductImageDTO;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Product;
import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.respons.PaginationInfo;
import com.example.foodshopbe.respons.ProductListResponse;
import com.example.foodshopbe.respons.ProductResponse;
import com.example.foodshopbe.services.Imp.IProductService;
import com.example.foodshopbe.services.Imp.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;
    private final ImageServiceImp imageServiceImp;

    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Product newProduct = iProductService.createProduct(productDTO);
            return ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/uploads/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable("id") int productId,
            @ModelAttribute("files") List<MultipartFile> files,
            @RequestParam(name =  "is_thumbnail") boolean isThumbnail
    ){
        try {
            Product existingProduct = iProductService.getProductById(productId);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if(files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
                return ResponseEntity.badRequest().body("Ban chi co the up toi da 10 anh");
            }
            List<ProductImage> productImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if(file.getSize() == 0) {
                    continue;
                }
                if(file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");
                }

                String filename = imageServiceImp.createStoreFile(file);
                ProductImage productImage = iProductService.createProductImage(
                        existingProduct.getId(),
                        ProductImageDTO.builder()
                                .imageURL(filename)
                                .isThumbnail(isThumbnail)
                                .build()
                );
                productImages.add(productImage);
            }
            return ResponseEntity.ok().body(productImages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-full")
    public ResponseEntity<List<Product>> getFullProducts() {
        List<Product> productList = iProductService.getFullProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/20desc")
    public ResponseEntity<?> get20ProductDesc() {
        List<Product> productList = iProductService.get20productDESC();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0", name = "category_id") Long categoryId,
            @RequestParam(defaultValue = "0", name="min_price") Double minPrice,
            @RequestParam(defaultValue = "0", name="max_price") Double maxPrice,
            @RequestParam(defaultValue = "asc", name = "sort_by_price") String sortByPriceParam,
            @RequestParam(defaultValue = "asc", name = "sort_by_date") String sortByDateParam,
            @RequestParam(defaultValue = "0", name = "is_promotion") Boolean isPromotion,
            @RequestParam(defaultValue = "0", name = "is_freeship") Boolean isFreeShip,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("id").descending());
        Page<ProductResponse> productPage = iProductService
                .getAllProducts(
                        keyword,
                        categoryId,
                        minPrice,
                        maxPrice,
                        sortByPriceParam,
                        sortByDateParam,
                        isFreeShip,
                        isPromotion,
                        pageRequest);

        int totalPages = productPage.getTotalPages();
        int _page = productPage.getNumber() + 1;
        int _limit = productPage.getSize();
        PaginationInfo paginationInfo = new PaginationInfo(totalPages,_page,_limit);
        List<ProductResponse> products = productPage.getContent();
        return ResponseEntity.ok(ProductListResponse
                .builder()
                .products(products)
                .pagination(paginationInfo)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById (
            @PathVariable("id") int productId
    ) {
           try {
               Product product = iProductService.getProductById(productId);
               return ResponseEntity.ok(product);
           }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            iProductService.deleteProduct(id);
            return ResponseEntity.ok(String.format("Da xoa product voi id = %d", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
