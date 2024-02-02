package com.example.foodshopbe.respons;

import com.example.foodshopbe.models.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImgResponse {

        @JsonProperty("image_url")
        private String imageURL;

        @JsonProperty("is_thumbnail")
        private Boolean isThumbnail;

        public static ProductImgResponse fromProductImage(ProductImage productImage) {
            ProductImgResponse producImgResponse = ProductImgResponse
                    .builder()
                    .imageURL(productImage.getImageURL())
                    .isThumbnail(productImage.getIsThumbnail())
                    .build();

            return producImgResponse;
        }
}
