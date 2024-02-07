package com.example.foodshopbe.services;


import com.example.foodshopbe.dtos.ProductDTO;
import com.example.foodshopbe.dtos.ProductImageDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.exceptions.InvalidParamException;
import com.example.foodshopbe.models.Category;
import com.example.foodshopbe.models.Product;
import com.example.foodshopbe.models.ProductImage;
import com.example.foodshopbe.models.ProductInfor;
import com.example.foodshopbe.repositories.CategoryRepository;
import com.example.foodshopbe.repositories.ProductImageRepository;
import com.example.foodshopbe.repositories.ProductInforRepository;
import com.example.foodshopbe.repositories.ProductRepository;
import com.example.foodshopbe.respons.ProductResponse;
import com.example.foodshopbe.services.Imp.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductInforRepository productInforRepository;

    private final ProductImageRepository productImageRepository;


    @Override
    public Product createProduct(ProductDTO productDTO) throws Exception {
        if(productRepository.existsByName(productDTO.getName())) {
            throw new DataNotFoundException("Name da ton tai vui long chon ten khac");
        }
        Category existingCategory = categoryRepository
                .findById(productDTO
                        .getCategoryId())
                        .orElseThrow(
                                ()-> new DataNotFoundException("Khong tim thay category voi id : "+ productDTO.getCategoryId())
                        );
        ProductInfor existingProductInfor = productInforRepository
                .findById(productDTO
                        .getProductInforId())
                        .orElseThrow(
                                ()-> new DataNotFoundException(("Khong tim thay product infor voi id: " + productDTO.getProductInforId()))
                        );

        Product newProduct = Product
                .builder()
                .name((productDTO.getName()))
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .quantity(productDTO.getQuantity())
                .listedPrice(productDTO.getListedPrice())
                .thumbnail(productDTO.getThumbnail())
                .productInfor(existingProductInfor)
                .discount(productDTO.getDiscount())
                .isPromotion(productDTO.getIsPromotion())
                .isFreeShip(productDTO.getIsFreeShip())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Page<ProductResponse> getAllProducts(String keyword,
                                                Long categoryId,
                                                Double minPrice,
                                                Double maxPrice,
                                                String sortByPriceParam,
                                                String sortByDateParam,
                                                Boolean isFreeShip,
                                                Boolean isPromotion,
                                                PageRequest pageRequest) {
        Page<Product> productsPage;
        productsPage = productRepository.searchProducts(
                categoryId,
                keyword,
                minPrice,
                maxPrice,
                sortByPriceParam,
                sortByDateParam,
                isPromotion,
                isFreeShip,
                pageRequest);

        return productsPage.map(ProductResponse::fromProduct);
    }

    @Override
    public Product getProductById(int id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException(
                        "Khong tim thay product voi id: " + id));
    }

    @Override
    public List<Product> get20productDESC() {
        return productRepository.findTop20ByOrderByIdDesc();
    }

    @Override
    public List<Product> getFullProducts() {
        return productRepository.findAll();
    }


    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(int id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if(existingProduct != null) {
            if(productRepository.existsByName(productDTO.getName())) {
                throw new DataNotFoundException("Name da ton tai vui long chon ten khac");
            }
            Category existingCategory = categoryRepository
                    .findById(productDTO
                            .getCategoryId())
                    .orElseThrow(
                            ()-> new DataNotFoundException("Khong tim thay category voi id : "+ productDTO.getCategoryId())
                    );

            ProductInfor existingProductInfor = productInforRepository
                    .findById(productDTO
                            .getProductInforId())
                    .orElseThrow(
                            ()-> new DataNotFoundException(("Khong tim thay product infor voi id: " + productDTO.getProductInforId()))
                    );

            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setQuantity(productDTO.getQuantity());
            existingProduct.setListedPrice(productDTO.getListedPrice());
            existingProduct.setProductInfor(existingProductInfor);
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setCategory(existingCategory);
            existingProduct.setDiscount(productDTO.getDiscount());
            existingProduct.setIsPromotion(productDTO.getIsPromotion());
            existingProduct.setIsFreeShip(productDTO.getIsFreeShip());


            productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) {
            productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ProductImage createProductImage(
            int productId,
            ProductImageDTO productImageDTO
    ) throws Exception {
        Product existingProduct = getProductById(productId);
        ProductImage newProductImage = ProductImage
                .builder()
                .product(existingProduct)
                .imageURL(productImageDTO.getImageURL())
                .isThumbnail(productImageDTO.getIsThumbnail())
                .build();

        int size = productImageRepository.findByProduct_Id(productId).size();
        if(size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new InvalidParamException("So anh phai <= " + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(newProductImage);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
