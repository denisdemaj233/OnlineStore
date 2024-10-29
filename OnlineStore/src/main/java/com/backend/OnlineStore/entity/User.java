package com.backend.OnlineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String city;

    private String zipCode;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    private List<Order> orders;

}
