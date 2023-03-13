package com.example.romeval.dataaccess;

import com.example.romeval.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
public interface TrainingRepository
        extends JpaRepository<Training, Long> {

    Optional<Training> findByAbbreviation(String abbreviation);

    @Query(value = "select t from Training t join fetch t.events ")
    List<Training> findAll();
}
