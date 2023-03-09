package com.example.romeval;

import com.example.romeval.domain.Event;
import com.example.romeval.domain.Trainer;
import com.example.romeval.domain.Training;
import com.example.romeval.model.EventStatus;
import com.example.romeval.model.TrainingStatus;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
class TestData {

    private TestData() {
    }

    static Training giveTraining() {
        LocalDate startDate = LocalDate.of(2023, Month.APRIL, 1);
        LocalTime startTime = LocalTime.of(9, 0);
        Trainer trainer = giveTrainer();
        Event event = Event.builder().location("Warsaw, Kolejowa 1").startTime(startTime).startDate(startDate)
            .finishTime(startTime.plus(8, ChronoUnit.HOURS)).finishDate(startDate.plusDays(3)).trainer(trainer)
            .totalSeats(10).status(EventStatus.RELEASED).build();
        return Training.builder().abbreviation("TRN-1").name("SpringBoot training")
            .description("During this training we will learn the SpringBoot basics.").provider("Bottega")
            .status(TrainingStatus.DRAFT).events(Collections.singleton(event)).build();
    }

    static Trainer giveTrainer() {

        return Trainer.builder().phoneNumber("123456789").name("Jan Kowalski").build();
    }
}
