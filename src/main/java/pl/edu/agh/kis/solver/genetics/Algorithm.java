package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.Schedule;

/**
 * @author Kruchy
 */
public class Algorithm {


    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;


    /**
     * Evolve the population with tournament selection.
     *
     * @param pop population
     * @return new population
     */
    public static SchedulePopulation evolvePopulation(SchedulePopulation pop, int maxWeight) {
        SchedulePopulation newSchedulePopulation = new SchedulePopulation(pop.size());



        // Best individual
        if (elitism) {
            newSchedulePopulation.saveIndividual(0, pop.getFittest(maxWeight));
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new schedules with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {

//            Individual indiv1 = tournamentSelection(pop, maxWeight);
//            Individual indiv2 = tournamentSelection(pop, maxWeight);
//            Individual newIndiv = crossover(indiv1, indiv2);
//
//            newPopulationImpl.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = 0; i < newSchedulePopulation.size(); i++) {
            mutate(newSchedulePopulation.getIndividual(i));
        }

        return newSchedulePopulation;
    }

    // Crossover schedules
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Schedule indiv) {
        // Loop through genes
//        for (int i = 0; i < indiv.size(); i++) {
//            if (Math.random() <= mutationRate) {
//                 Create random gene
//                byte gene = (byte) Math.round(Math.random());
//                indiv.setGene(i, gene);
//
//            }
//        }
    }

    // Select schedules for crossover
    private static Schedule tournamentSelection(SchedulePopulation pop, int maxWeight) {
        // Create a tournament population
        SchedulePopulation tournament = new SchedulePopulation(tournamentSize);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Schedule fittest = tournament.getFittest(maxWeight);
        return fittest;
    }
}
