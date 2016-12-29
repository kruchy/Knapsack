package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class FitnessCalc {

    static List<Schedule> solution = new ArrayList<>();
    private static int maxWeight;

    public static int getMaxWeight() {
        return maxWeight;
    }


    public static void setMaxWeight(int maxWeight) {
        FitnessCalc.maxWeight = maxWeight;
    }

    public static void setSolution(List<Schedule> newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution 
    // with string of 0s and 1s
    public static void setSolution(String newSolution) {
        solution = new ArrayList<>();
        // Loop through each character of our string and save it in our byte 
        // array

    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    public static float getFitness(Schedule schedule)
    {
        int fitness = 0, value = 0, weight = 0;
        //        // Loop through our schedules genes and compare them to our cadidates
        //        for (int i = 0; i < individual.size(); i++) {
        //
        //        	if(individual.getGene(i) == 1 ){
        //        		value += Individual.values[i];
        //            	weight += Individual.weights[i];
        //
        //        	}
        //        }
        //        if(weight > FitnessCalc.getMaxWeight()) return 0 ;
       // COUNT TIME
        // IF TIME > MAX RETURN 0

        return value;
    }

    // Get optimum fitness
    public static int getMaxFitness() {
        return 9999;
    }
}
