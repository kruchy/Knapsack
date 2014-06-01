package genetics;

/**
 * Wszêdzie wciska³em maxWeight ¿eby to jakoœ ograniczyc
 * 
 * @author Kruchy
 *
 */
public class Algorithm {

    
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
   
    /* Public methods */
    
    /**
     * Evolve the population with tournament selection.
     * @param pop population
     * @return  new population
     */
    public static Population evolvePopulation(Population pop,int maxWeight) {
        Population newPopulation = new Population(pop.size(), false);

        // Best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest(maxWeight));
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
      // Loop over the population size and create new individuals with
        // crossover
        for (int i = 0; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop,maxWeight);
            Individual indiv2 = tournamentSelection(pop,maxWeight);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
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
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
           
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop,int maxWeight) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest(maxWeight);
        return fittest;
    }
}