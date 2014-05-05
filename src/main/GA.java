package main;
import genetics.Algorithm;
import genetics.FitnessCalc;
import genetics.Population;
import genetics.*;
public class GA {

    public static void main(String[] args) {

        // Set a candidate solution
        FitnessCalc.setMaxWeight(50);
        int maxWeight = FitnessCalc.getMaxWeight();
        int[] vals = {1,2,3,5,6,7,8,9,11,2,3,4,3,5,2,1,5,6,5,7,3,4,5,2,21,5,6,3,4,2,6,3,4,2,1,5,1,2,4,6,8,5,4,2,3,4,6,23,43,5,2};
        int[] weis = {2,3,4,1,5,2,6,3,3,22,5,4,3,4,6,3,2,3,3,4,6,3,4,5,11,1,2,3,5,3,4,5,6,5,5,4,3,2,2,4,6,7,5,6,7,4,3,10,15,2,2};
        double tolerance = 0.05;
        float breakpoint1=0,breakpoint2=0;
        Individual.setDefaultGeneLength(weis.length);
        Individual.values = vals ;
        Individual.weights = weis;
        // Create an initial population
        Population myPop = new Population(50, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        Individual fittest;
        do{
        	breakpoint1 = breakpoint2;
        	fittest = myPop.getFittest(maxWeight);
        	breakpoint2 = fittest.getFitness(fittest.id) ;
        	generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + fittest.getFitness(fittest.id));
            myPop = Algorithm.evolvePopulation(myPop,maxWeight);
            /*dis nids update */
            
        }
    	while (breakpoint2 - breakpoint1 > tolerance  & generationCount <= 1000);
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest(maxWeight));

    }
}
