package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id", nullable = false, length = 20)
    private String postId;

    private String prefecture;
    private String address1;
    private String address2;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
