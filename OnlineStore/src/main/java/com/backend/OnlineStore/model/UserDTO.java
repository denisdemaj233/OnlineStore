package com.backend.OnlineStore.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String city;
    private String country;
    private String street;
    private String zipCode;
    private String avatarUrl;
    private String roleName;


}
