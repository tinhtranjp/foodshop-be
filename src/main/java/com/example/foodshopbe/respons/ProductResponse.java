package com.example.foodshopbe.respons;
import com.example.foodshopbe.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse extends BaseResponse{
    private int id;
    private String name;
    private Float price;
    private String description;
    private int quantity;
    private String thumbnail;

    @JsonProperty("is_promotion")
    private Boolean isPromotion;

    @JsonProperty("listed_price")
    private Float listedPrice;

    @JsonProperty("is_freeship")
    private Boolean isFreeShip;

    private Float discount;

    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("product_infor_id")
    private int productInforId;

    public static ProductResponse fromProduct(Product product) {

        ProductResponse productResponse = ProductResponse
                .builder()
                .discount(product.getDiscount())
                .isFreeShip(product.getIsFreeShip())
                .isPromotion(product.getIsPromotion())
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .quantity(product.getQuantity())
                .listedPrice(product.getListedPrice())
                .categoryId(product.getCategory().getId())
                .productInforId(product.getProductInfor().getId())
                .build();
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        return productResponse;
    }
}
