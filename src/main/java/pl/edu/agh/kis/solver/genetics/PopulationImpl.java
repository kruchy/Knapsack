package pl.edu.agh.kis.solver.genetics;


import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.List;

public class PopulationImpl implements Population {

    Schedule[] schedules;

    /*
     * Constructors
     */
    // Create a population
    public PopulationImpl(int populationSize, boolean initialise) {
        schedules = new Schedule[populationSize];

        // Initialize population
//        if (initialise) {
//             Loop and create schedules
//            for (int i = 0; i < size(); i++) {
//                Schedule newIndividual = new Schedule(i);
//                newIndividual.generateZeroSchedule();
//                saveIndividual(i, newIndividual);
//            }
//        }

    }


    /* Getters */
    public Schedule getIndividual(int index) {
        return schedules[index];
    }

    public Schedule getFittest(int maxWeight) {
        Schedule fittest = schedules[0];
        // Loop through schedules to find fittest
        for (int i = 0; i < size(); i++) {
            Schedule tmp = getIndividual(i);
            if (fittest.getFitness(fittest.id) < tmp.getFitness(tmp.id)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public Schedule getWorst(int maxWeight) {
        int j = 0;
        Schedule worst = getFittest(maxWeight);

        // Loop through schedules to find fittest
        for (int i = 0; i < size(); i++) {
            Schedule tmp = getIndividual(i);
            if (worst.getFitness(worst.id) > tmp.getFitness(tmp.id) && tmp.getFitness(tmp.id) > 0) {
                worst = getIndividual(i);
            }
        }
        return worst;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return schedules.length;
    }

    // Save individual
    public void saveIndividual(int index, Schedule schedule) {
        schedules[index] = schedule;
    }


    @Override
    public void generatePopulation() {

    }

    @Override
    public void mutate() {

    }

    @Override
    public void crossover(Schedule schedule1, Schedule schedule2) {

    }

    @Override
    public List<Schedule> selectFittest(FitnessCalculator calculator) {
        return null;
    }
}