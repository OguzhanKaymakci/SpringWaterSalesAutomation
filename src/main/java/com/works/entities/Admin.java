package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Admin name can not be blank")
    @Length(message = "Admin name must contain min 2 max  5O character.", min = 2, max = 50)
    private String adminName;

    @NotBlank(message = "admin surname can not be blank")
    @Length(message = "Admin surname must contain min 2 max  5O character.", min = 2, max = 50)
    private String adminSurname;
    @Length(message = "Maximum 60", max = 60)
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email Format Error")
    private String email;

    //    @Length(message = "Maximum 5 min 10",min = 5, max = 10)
    @NotBlank(message = "password can not be blank")
    @Pattern(message = "Password must contain min one upper,lower letter and 0-9 digit number ",
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{6,}")
//    @Pattern(message = "Password must contain min one upper,lower letter and 0-9 digit number ", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$)")
    private String password;

    private boolean enabled;
    private boolean tokenExpired;
    private Role roles;
}
