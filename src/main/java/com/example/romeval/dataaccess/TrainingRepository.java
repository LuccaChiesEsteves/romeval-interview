package com.example.romeval.dataaccess;

import com.example.romeval.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
public interface TrainingRepository
        extends JpaRepository<Training, Long> {

}
