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
    public int id;
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
}
