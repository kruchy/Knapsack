package pl.edu.agh.kis.solver.genetics.model;

import pl.edu.agh.kis.solver.genetics.FitnessCalc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Schedule {

    static int defaultDetailNumber = 4;

    public static int[] values;
    public static int[] weights;
    private float fitness = 0;
    public Integer id;
    private Map<Machine, List<Process>> machinesProcesses;

    public Schedule() {
    }

    public Schedule(int id) {
        this.id = id;
    }

    public Map<Machine, List<Process>> getSchedule() {
        return this.machinesProcesses;
    }

    public void generateSchedule() {
        machinesProcesses = new HashMap<>();
    }

    public void generateZeroSchedule(List<Process> processes) {
        machinesProcesses = processes.stream().collect(groupingBy(Process::getMachine));
    }

    public static void setDefaultDetailNumber(int length) {
        defaultDetailNumber = length;
        values = new int[defaultDetailNumber];
        weights = new int[defaultDetailNumber];
    }


    public float getFitness(int id) {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    public static Schedule randomSchedule() {
        return new Schedule();
    }

    public Machine getMachine(int id) {
        return machinesProcesses
                .keySet()
                .stream()
                .filter(machine -> machine.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Machine> getMachines() {
        return machinesProcesses
                .keySet()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Detail> getDetails() {
        return machinesProcesses
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .map(Process::getDetail)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Process> getJobsForDetail(Integer id) {
        return machinesProcesses.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .filter(process -> process.getDetail().getId().equals(id))
                .collect(Collectors.toList());

    }

    public boolean isAnyProcessOverlapping() {
        boolean isOverlapping = false;
        for (Detail detail : getDetails()) {
            List<Process> jobsForDetail = getJobsForDetail(detail.getId());
            isOverlapping = isAnyProcessOverlapping(jobsForDetail);
        }
        for (Map.Entry<Machine, List<Process>> machineListEntry : machinesProcesses.entrySet()) {
            List<Process> processes = machineListEntry.getValue();
            isOverlapping = isAnyProcessOverlapping(processes);
        }
        return isOverlapping;
    }

    private boolean isAnyProcessOverlapping(List<Process> processes) {
        boolean isOverlapping = false;
        for (int i = 0; i < processes.size() - 1; i++) {
            Process process = processes.get(i);
            Process nextProcess = processes.get(i + 1);
            if (process.getStartTime() + process.getOperationTime() <= nextProcess.getStartTime()) {
                isOverlapping = true;
            }
        }
        return isOverlapping;
    }
}
