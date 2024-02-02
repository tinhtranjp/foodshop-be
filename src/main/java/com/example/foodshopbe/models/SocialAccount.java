package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "social_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20)
    private String provier;
    @Column(length = 150)
    private String email;
    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
