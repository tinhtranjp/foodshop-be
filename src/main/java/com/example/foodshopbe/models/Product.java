package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 350)
    private String name;

    private Float price;

    private String description;

    private int quantity;

    @Column(name = "is_freeship", length = 10)
    private Boolean isFreeShip;

    @Column(name = "is_promotion", length = 10)
    private Boolean isPromotion;

    private Float discount;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "listed_price")
    private Float listedPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

//    @OneToMany(mappedBy = "product")
//    List<ProductImage> productImageList;

//    @OneToMany(mappedBy = "product")
//    List<RatingFood> ratingFoodList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_infor_id", referencedColumnName = "id")
    private ProductInfor productInfor;

}
