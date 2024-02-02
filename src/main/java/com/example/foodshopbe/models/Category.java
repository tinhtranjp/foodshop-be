package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 100)
    private String name;
//    @OneToMany(mappedBy = "category")
//    List<Product> productList;

//    @OneToMany(mappedBy = "category")
//    List<ParentCategory> parentCategoryList;
}
