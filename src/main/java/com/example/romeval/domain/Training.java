package com.example.romeval.domain;

import com.example.romeval.model.TrainingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private TrainingStatus status;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, length = 30, unique = true)
    private String abbreviation;

    @Column(nullable = false)
    private String description;

    @OneToMany(/*mappedBy = "training",*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Set<Event> events;

}
