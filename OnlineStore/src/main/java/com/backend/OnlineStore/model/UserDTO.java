package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserDTO {

    private String email;
    private String city;
    private String zipCode;


}
