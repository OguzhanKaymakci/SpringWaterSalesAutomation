package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "Not Blank ")
    private String phoneNum;
    @NotBlank(message = "Not Blank ")
    @Length(max = 95, message = "max 95")
    private String address;
}
