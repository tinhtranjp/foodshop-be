package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String name;

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
//    @OneToMany(mappedBy = "role")
//    List<User> userList;
}
