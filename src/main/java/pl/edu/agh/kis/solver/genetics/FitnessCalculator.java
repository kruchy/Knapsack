package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.*;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.*;

import static java.util.Optional.of;

public class FitnessCalculator {

    public static final int OVERLAPPING_PENALTY = 300;
    public static final int NO_PENALTY = 0;
    static List<Schedule> solution = new ArrayList<>();
    private static int WORST_FITNESS = 9999;

    public int getFitness(Schedule schedule) {

        int fitness = 0;

//        fitness += getPenaltyForOverlapping(schedule);
//        fitness += getDelaysForDetails(schedule);
//        fitness += getDelaysForMachines(schedule);
//        fitness += getPenaltiesForDetails(schedule);
        return schedule
                .getSchedule()
                .stream()
                .map(DetailProcessQueue::getProcesses)
                .flatMap(Collection::stream)
                .map(Process::getOperationTime)
                .reduce((integer, integer2) -> integer + integer2)
                .orElse(99999);
    }

    private int getPenaltyForOverlapping(Schedule schedule) {
        return of(schedule)
                .filter(Schedule::isOverlapping)
                .map(schedule1 -> OVERLAPPING_PENALTY)
                .orElse(NO_PENALTY);
    }

    public int getPenaltiesForDetails(Schedule schedule) {
        return schedule
                .getDetailSchedule()
                .values()
                .stream()
                .map(this::getLastJobPenalty)
                .reduce(0, (integer, integer2) -> integer + integer2);
    }

    public int getDelaysForDetails(Schedule schedule) {
        int result = 0;
        Set<Map.Entry<Detail, List<Process>>> entries = schedule.getDetailSchedule().entrySet();
        for (Map.Entry<Detail, List<Process>> detailProcess : entries) {
            List<Process> processes = detailProcess.getValue();
            result += getDelays(processes);
        }
        return result;
    }

    public int getDelaysForMachines(Schedule schedule) {
        int result = 0;
        Set<Map.Entry<Machine, List<Process>>> machineEntries = schedule.getMachineSchedule().entrySet();
        for (Map.Entry<Machine, List<Process>> machineEntry : machineEntries) {
            List<Process> processes = machineEntry.getValue();
            for (int i = 0; i < processes.size() - 1; i++) {
                Process nextProcess = processes.get(i + 1);
                Process process = processes.get(i);
                result += nextProcess.getStartTime() - (process.getStartTime() + process.getOperationTime());
            }
        }
        return result;
    }

    public int getDelays(List<Process> jobsForDetail) {
        int delays = 0;
        for (int i = 0; i < jobsForDetail.size() - 1; i++) {
            Process process = jobsForDetail.get(i);
            Process nextProcess = jobsForDetail.get(i + 1);
            delays += nextProcess.getStartTime() - (process.getStartTime() + process.getOperationTime());
        }
        return delays;
    }


    public Integer getLastJobPenalty(List<Process> jobsForDetail) {
        return jobsForDetail
                .stream()
                .reduce((first, second) -> second)
                .map(this::calculatePenalty)
                .orElse(0);
    }

    private Integer calculatePenalty(Process process) {
        Integer delay = process.getStartTime() + process.getOperationTime() - process.getDetail().getMaxFinishTime();
        return delay < 0 ? 0 : delay;
    }


}