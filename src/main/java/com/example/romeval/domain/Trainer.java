package com.example.romeval.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, length = 9)
    private String phoneNumber;

}
