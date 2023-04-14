package com.example.postman_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Setter
@ToString
@Entity
public class Poslyga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

}
