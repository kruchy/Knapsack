package main;
import genetics.*;
public class GA {

    public static void main(String[] args) {

        // Set a candidate solution
        int[] vals = {1,2,3,5,6,7,8,9,11,2,3,4,3,5,2,1,5,6,5,7,3,4,5,2,21,5,6,3,4,2,6,3,4,2,1,5,1,2,4,6,8,5,4,2,3,4,6,23,43,5,2};
        int[] weis = {2,3,4,1,5,2,6,3,3,22,5,4,3,4,6,3,2,3,3,4,6,3,4,5,11,1,2,3,5,3,4,5,6,5,5,4,3,2,2,4,6,7,5,6,7,4,3,10,15,2,2};
        // Create an initial population
        FitnessCalc.setMaxWeight(10);
        int maxWeight = FitnessCalc.getMaxWeight();
        double tolerance = 0.05;
        Individual.setDefaultGeneLength(weis.length);
        Individual.values = vals ;
        Individual.weights = weis;
        Population myPop = new Population(50, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        Individual fittest;
        float breakpoint1=0,breakpoint2=0;
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
        System.out.println("Genes:ghujghu");System.out.println("chuj");
        System.out.println(myPop.getFittest(maxWeight));
        Individual tmp = myPop.getFittest(maxWeight);
        int value = 0, weight = 0;
		for(int i = 0; i < tmp.values.length; i++)
		{ 
			if(tmp.getGene(i)== 1){
				value += tmp.values[i];
				weight += tmp.weights[i];
			}
			
			
		}
		System.out.println("\n" + value + ", waga :" + weight + "\n" );
    }
}
