package com.example.romeval.dto;

import com.example.romeval.domain.Event;
import com.example.romeval.model.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EventDto {
    private Long id;
    private EventStatus status;
    private String location;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Integer totalSeats;

    public EventDto(Event event) {
        id = event.getId();
        status = event.getStatus();
        location = event.getLocation();
        startDate = event.getStartDate();
        finishDate = event.getFinishDate();
        startTime = event.getStartTime();
        finishTime = event.getFinishTime();
        totalSeats = event.getTotalSeats();
    }
}
