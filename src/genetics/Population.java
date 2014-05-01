package genetics;
public class Population {
	
	Individual[] individuals;
	private static int maxWeight;
    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        
        // Initialize population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual(i);
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
       
    }
    
    
    
    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest(int maxWeight) {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
        	Individual tmp = getIndividual(i) ;
            if (fittest.getFitness(fittest.id) <= tmp.getFitness(tmp.id)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }



	public static int getMaxWeight() {
		return maxWeight;
	}



	public static void setMaxWeight(int maxWeight) {
		Population.maxWeight = maxWeight;
	}
}