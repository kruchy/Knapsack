package main;
import genetics.Algorithm;
import genetics.FitnessCalc;
import genetics.Population;
import genetics.*;
public class GA {

    public static void main(String[] args) {

        // Set a candidate solution
        FitnessCalc.setSolution("1111000000000000000000000000000000000000001111000000000000001111");
        FitnessCalc.setMaxWeight(5);
        int maxWeight = FitnessCalc.getMaxWeight();
        int[] vals = {1,2,3};
        int[] weis = {2,3,4};
        Individual.setDefaultGeneLength(3);
        Individual.values = vals ;
        Individual.weights = weis;
        
        // Create an initial population
        Population myPop = new Population(50, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        Individual fittest;
        do{
        	fittest = myPop.getFittest(maxWeight);
        	generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + fittest.getFitness(fittest.id));
            myPop = Algorithm.evolvePopulation(myPop,maxWeight);
        }
    	while (fittest.getFitness(fittest.id) < FitnessCalc.getMaxFitness() & generationCount <= 1000);
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest(maxWeight));

    }
}
