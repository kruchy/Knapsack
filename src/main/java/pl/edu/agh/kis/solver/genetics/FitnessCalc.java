package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static float getFitness(Schedule schedule) {
        int value = 0, weight = 0;
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

        long fitness = 0;

        schedule.isAnyProcessOverlapping();

        for (Detail detail : schedule.getDetails()) {
            List<Process> jobsForDetail = schedule.getJobsForDetail(detail.getId());
            fitness += getDelays(jobsForDetail);
            fitness += getPenaltyForLastJob(detail, jobsForDetail)
                    .orElse(detail.getMaxFinishTime()) - detail.getMaxFinishTime();
        }


        return value;
    }

    private static long getDelays(List<Process> jobsForDetail) {
        long delays = 0;
        for (int i = 0; i < jobsForDetail.size() - 1; i++) {
            Process process = jobsForDetail.get(i);
            Process nextProcess = jobsForDetail.get(i + 1);
            delays += nextProcess.getStartTime() - process.getStartTime();
        }
        return delays;
    }


    private static Optional<Integer> getPenaltyForLastJob(Detail detail, List<Process> jobsForDetail) {
        return jobsForDetail
                .stream()
                .reduce((first, second) -> second)
                .map(process -> process.getStartTime() + process.getOperationTime());
    }

    // Get optimum fitness
    public static int getMaxFitness() {
        return 9999;
    }
}