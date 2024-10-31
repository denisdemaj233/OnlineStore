package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;

    private String city;
    private String zipCode;
    private Long roli; // ADMIN/USER
    private String password;


}
