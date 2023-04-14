package com.example.postman_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
@Setter
@Entity
public class Pokaznuk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal tarif;
    private String attribute;

}
