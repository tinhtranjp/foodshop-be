package com.example.foodshopbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details1")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String note;

    private Float price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orders1_id")
    @JsonBackReference
    Order1 order1;
}
