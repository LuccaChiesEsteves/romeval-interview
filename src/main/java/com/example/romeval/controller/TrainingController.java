package com.example.romeval.controller;

import com.example.romeval.business.TrainingService;
import com.example.romeval.domain.Event;
import com.example.romeval.domain.Trainer;
import com.example.romeval.domain.Training;
import com.example.romeval.dto.EventDto;
import com.example.romeval.model.EventStatus;
import com.example.romeval.model.TrainingStatus;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/{trainingId}/events")
    @ResponseStatus(HttpStatus.OK)
    public Set<EventDto> getEventsByTrainingId(@PathVariable @NotNull Long trainingId) {
        return trainingService.getEventsByTrainingId(trainingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createTestTraining() {
        Long id = trainingService.save(giveTraining());
        return id.toString();
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
