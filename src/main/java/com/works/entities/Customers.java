package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Not Blank ")
    @Length(max = 25, message = "max 25")
    private String name;
    @NotBlank(message = "Not Blank ")
    @Length(max = 25, message = "max 25")
    private String surName;
    @Length(message = "Maximum 60", max = 60)
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email Format Error")
    private String email;
    @NotBlank(message = "Not Blank ")
    private String phoneNum;
    @NotBlank(message = "Not Blank ")
    @Length(max = 95, message = "max 95")
    private String address;
    @Pattern(message = "Password must contain min one upper,lower letter and 0-9 digit number ",
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{6,}")
//    @Pattern(message = "Password must contain min one upper,lower letter and 0-9 digit number ", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$)")
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "role_Id",referencedColumnName = "id")
    private Role roles;
}
