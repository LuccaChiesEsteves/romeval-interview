package com.example.romeval.business;

import com.example.romeval.dataaccess.TrainingRepository;
import com.example.romeval.domain.Trainer;
import com.example.romeval.domain.Training;
import com.example.romeval.dto.EventDto;
import com.example.romeval.exception.RomEvalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final NotificationService notificationService;

    private static final String NOTIFICATION_MESSAGE = "Training {1} has been created";

    private static final String UNIQUE_ABBREVIATION_ERROR = "Training abbreviation must be unique";

    /**
     * Saves the Training and notifies the trainer about new event
     *
     * @param training to be saved
     * @return the new Training identifier
     */
    public Long save(Training training) {
        try {
            trainingRepository.save(training);
            notifyTrainers(training, MessageFormat.format(NOTIFICATION_MESSAGE, training.getName()));
        } catch (Exception e) {
            throw new RomEvalException(UNIQUE_ABBREVIATION_ERROR);
        }

        return training.getId();
    }

    public Set<EventDto> getEventsByTrainingId(Long trainingId) {
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new EntityNotFoundException("Training not found"));
        return  training.getEvents().stream().map(EventDto::new).collect(Collectors.toSet());
    }
    private void notifyTrainers(Training training, String message) {
        if(ObjectUtils.isEmpty(training.getEvents())) {
            log.warn("No events associated to this training, unable to send notifications.");
            return;
        }
        Set<Trainer> trainers = new java.util.HashSet<>(Collections.emptySet());
        training.getEvents().forEach(event -> trainers.add(event.getTrainer()));
        trainers.forEach(trainer -> notificationService.notifyTrainerAboutEvent(trainer, message));
    }
}
