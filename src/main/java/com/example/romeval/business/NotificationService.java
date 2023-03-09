package com.example.romeval.business;

import com.example.romeval.domain.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@Component
@RequiredArgsConstructor
public class NotificationService {

    /**
     * Created for test purposes, should not be implemented
     * @param trainer
     * @param message
     */
    public void notifyTrainerAboutEvent(Trainer trainer, String message) {
        //        NOP
    }

}
