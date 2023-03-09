package com.example.romeval;

import com.example.romeval.business.NotificationService;
import com.example.romeval.business.TrainingService;
import com.example.romeval.domain.Training;
import com.example.romeval.exception.RomEvalException;
import com.example.romeval.model.TrainingStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TrainingServiceTest {

    public static final Training TRAINING = Training.builder().abbreviation("TRN-1").name("SpringBoot training")
        .description("During this training we will learn the SpringBoot basics.").provider("Bottega")
        .status(TrainingStatus.DRAFT).build();

    @InjectMocks
    @Autowired
    private TrainingService underTest;

    @MockBean
    private NotificationService notificationService;

    @Test
    void shouldReturnIdAfterSavingTraining() {
        //given
        //when
        Long id = underTest.save(TestData.giveTraining());
        //then
        assertThat(id).isNotNull().isPositive();
    }

    @Test
    void shouldNotifyTrainerAboutNewEventWhenEventsAreCreatedAlongTrainings() {
        //given
        //when
        underTest.save(TestData.giveTraining());
        //then
        // remember that only mocks can be verified
        verify(notificationService).notifyTrainerAboutEvent(
            ArgumentMatchers.argThat(e -> e.getName().equals("Jan Kowalski")), any(String.class));
    }

    // This test needed to be changed because it was saving the same entity and not raising the exception
    @Test
    void shouldThrowExceptionWhenTrainingWithExistingAbbreviationExists() {
        //given
        underTest.save(TRAINING);
        //when
        Training badTraining = Training.builder().abbreviation("TRN-1").name("SpringBoot training")
                .description("During this training we will learn the SpringBoot basics.").provider("Bottega")
                .status(TrainingStatus.DRAFT).build();
        assertThatThrownBy(() -> underTest.save(badTraining)).isInstanceOf(RomEvalException.class)
            .hasMessage("Training abbreviation must be unique");
    }
}
