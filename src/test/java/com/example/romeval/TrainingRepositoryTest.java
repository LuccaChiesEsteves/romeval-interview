package com.example.romeval;

import com.example.romeval.dataaccess.TrainingRepository;
import com.example.romeval.domain.Event;
import com.example.romeval.domain.Trainer;
import com.example.romeval.domain.Training;
import com.example.romeval.model.EventStatus;
import com.example.romeval.model.TrainingStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.transaction.TestTransaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@DataJpaTest
@Slf4j
class TrainingRepositoryTest {

    @Autowired
    private TrainingRepository underTest;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {

        //        prepare the entities
        LocalDate startDate = LocalDate.of(2023, Month.APRIL, 1);
        LocalTime startTime = LocalTime.of(9, 0);
        Trainer trainer = Trainer.builder().phoneNumber("123456789").name("Jan Kowalski").build();
        Event event = Event.builder().location("Warsaw, Kolejowa 1").startTime(startTime).startDate(startDate)
            .finishTime(startTime.plus(8, ChronoUnit.HOURS)).finishDate(startDate.plusDays(3)).trainer(trainer)
            .totalSeats(10).status(EventStatus.RELEASED).build();
        Training training = Training.builder().abbreviation("TRN-1").name("SpringBoot training")
            .description("During this training we will learn the SpringBoot basics.").provider("Bottega")
            .status(TrainingStatus.DRAFT).events(Collections.singleton(event)).build();
        underTest.save(training);
        // Commit to persist the data into db, by default
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @AfterEach
    void teardown() {

        underTest.deleteAll();
    }

    @Test
    void shouldRetrieveEventsAlongTrainings() {

        //given
        // start a new transaction, because the previous was committed
        TestTransaction.start();
        // clear the Persistence Context to void reading the entity from there instead of db
        entityManager.clear();

        //        when
        List<Training> training = underTest.findAll();
        //        End transaction to avoid reading lazy dependencies in Transactional scope - that is not the expected result in this test
        TestTransaction.flagForCommit();
        TestTransaction.end();

        //        then
        assertThat(training).hasSize(1);
        assertThat(training.get(0).getEvents()).hasSize(1);
    }
}
