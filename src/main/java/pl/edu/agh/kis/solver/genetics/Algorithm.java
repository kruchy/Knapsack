package pl.edu.agh.kis.solver.genetics;

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
    public static SchedulePopulation evolvePopulation(SchedulePopulation pop) {
        SchedulePopulation newSchedulePopulation = new SchedulePopulation(2);



        // Best individual
        if (elitism) {
//            newSchedulePopulation.saveIndividual(0, pop.selectFittest(new FitnessCalculator()));
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
//        for (int i = elitismOffset; i < pop.size(); i++) {

//            Individual indiv1 = tournamentSelection(pop, maxWeight);
//            Individual indiv2 = tournamentSelection(pop, maxWeight);
//            Individual newIndiv = crossover(indiv1, indiv2);
//
//            newPopulationImpl.saveIndividual(i, newIndiv);
//        }

        // Mutate population
//        for (int i = 0; i < newSchedulePopulation.size(); i++) {
//            mutate(newSchedulePopulation.getIndividual(i));
//        }

        return newSchedulePopulation;
    }

}
