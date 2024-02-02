package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product_infor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 250)
    private String material;

    @Column(length = 50)
    private String amount;
    @Column(name = "expiratetion_date",length = 50)
    private String expiratetionDate;

    @Column(name = "storage_method",length = 250)
    private String storageMethod;

    @Column(name = "safery_instruc")
    private String saferyInstruc;

//    @OneToOne(mappedBy = "productInfor")
//    private Product product;
}
