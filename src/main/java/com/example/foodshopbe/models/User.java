package com.example.foodshopbe.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends  BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "furigana_name", length = 50)
    private String furiganaName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "fax_number", length = 20)
    private String faxNumber;

    private Boolean gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email_accept")
    private Boolean emailAccept;

    @Column(name = "login_id", length = 50, nullable = false)
    private String loginId;

    @Column(name = "pass_word", length = 250)
    private String password;

    @Column(name = "facebook_account_id")
    private int facebookAccountId;

    @Column(name = "google_account_id")
    private int googleAccountId;

    @Column(length = 100)
    private String email;

    @Column(name = "is_active")
    private Boolean isActive = true;

//    @OneToMany(mappedBy = "user")
//    private List<Address> addresseList;
//
//    @OneToMany(mappedBy = "user")
//    private List<Order> orderList;
//
//    @OneToMany(mappedBy = "user")
//    private List<RatingFood> ratingFoodList;
//
//    @OneToMany(mappedBy = "user")
//    private List<SocialAccount> socialAccountList;
//
//    @OneToMany(mappedBy = "user")
//    private List<Token> tokenList;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+getRole().getName().toUpperCase()));

        return authorityList;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
