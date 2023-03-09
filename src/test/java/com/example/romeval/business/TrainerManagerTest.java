package com.example.romeval.business;

import com.example.romeval.domain.Trainer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TrainerManagerTest {

    private final TrainerManager underTest = new TrainerManager();

    @Test
    void createTrainersDiff() {
        //        given
        Trainer paul_deitel = Trainer.builder().name("Paul Deitel").build();
        Trainer robert_martin = Trainer.builder().name("Robert C. Martin").build();
        Trainer martin_fowler = Trainer.builder().name("Martin Fowler").build();
        Trainer kent_beck = Trainer.builder().name("Kent Beck").build();
        Trainer eric_evans = Trainer.builder().name("Eric Evans").build();
        Trainer keith_ross = Trainer.builder().name("Keith W. Ross").build();
        //        when
        TrainerManager.TrainerDiff trainersDiff =
            underTest.createTrainersDiff(Arrays.asList(paul_deitel, robert_martin, kent_beck),
                Arrays.asList(martin_fowler, kent_beck, eric_evans, robert_martin, keith_ross));
        //        then
        assertThat(trainersDiff.getNewTrainers()).containsExactly(eric_evans, keith_ross, martin_fowler);
        assertThat(trainersDiff.getOldTrainers()).containsExactly(paul_deitel);
    }
}
