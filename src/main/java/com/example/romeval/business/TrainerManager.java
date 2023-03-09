package com.example.romeval.business;

import com.example.romeval.domain.Trainer;

import java.util.*;

/**
 * Sebastian Gruchot created on 03.03.2023
 */
class TrainerManager {

    /**
     * This method should find the new and old Trainers based on the difference in the method parameters
     *
     * @param previousTrainers Trainers that were conducting trainings
     * @param futureTrainers   Trainers that will be conducting trainings in the future
     * @return return a diff of type {@link TrainerDiff}
     */
    TrainerDiff createTrainersDiff(final Collection<Trainer> previousTrainers,
                                   final Collection<Trainer> futureTrainers) {
        List<Trainer> oldTrainers = new ArrayList<>(previousTrainers);
        List<Trainer> newTrainers = new ArrayList<>(futureTrainers);
        futureTrainers.forEach(
               trainer -> {
                   if (oldTrainers.contains(trainer)) {
                       oldTrainers.remove(trainer);
                       newTrainers.remove(trainer);
                   }
               });
        oldTrainers.sort(new TrainerComparator());
        newTrainers.sort(new TrainerComparator());
        return new TrainerDiff(newTrainers, oldTrainers);
    }

    private static class TrainerComparator implements Comparator<Trainer> {
        @Override
        public int compare(Trainer o1, Trainer o2) {
            return o1.getName().compareTo(o2.getName());
        }

    }

    /**
     * This class contains the new and old Trainers sorted alphabetically by their name
     */
    static class TrainerDiff {

        private final Collection<Trainer> newTrainers;

        private final Collection<Trainer> oldTrainers;

        public TrainerDiff(Collection<Trainer> newTrainers, Collection<Trainer> oldTrainers) {

            this.newTrainers = newTrainers;
            this.oldTrainers = oldTrainers;
        }

        public Collection<Trainer> getNewTrainers() {

            return newTrainers;
        }

        public Collection<Trainer> getOldTrainers() {

            return oldTrainers;
        }
    }
}
