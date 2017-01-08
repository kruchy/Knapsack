package pl.edu.agh.kis.solver.genetics.model;

import pl.edu.agh.kis.solver.genetics.FitnessCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Schedule {

    static int defaultDetailNumber = 4;

    public static int[] values;
    public static int[] weights;
    private float fitness = 0;
    public Integer id;
    private Map<Machine, List<Process>> machinesProcesses;
    private List<Detail> details;
    private List<Machine> machines;
    private List<Process> processes;

    public Schedule(List<Detail> details, List<Machine> machines, List<Process> processes) {
        this.details = details;
        this.machines = machines;
        this.processes = processes.stream()
                .sorted(comparing(p -> ((Process) p).getMachine().getId())
                        .thenComparing(comparing(o -> ((Process) o).getStartTime())))
                .collect(Collectors.toList());
        generateZeroSchedule(processes);
    }

    public Schedule() {
    }

    public Schedule(int id) {
        this.id = id;
    }

    public Schedule(List<Process> loadedProcesses) {
        this.processes = loadedProcesses.stream()
                .sorted(comparing(p -> ((Process) p).getMachine().getId())
                        .thenComparing(comparing(o -> ((Process) o).getStartTime())))
                .collect(Collectors.toList());
        this.machines = loadedProcesses
                .stream()
                .map(Process::getMachine)
                .distinct()
                .sorted(Comparator.comparing(Machine::getId))
                .collect(Collectors.toList());
        this.details = loadedProcesses
                .stream()
                .map(Process::getDetail)
                .distinct()
                .sorted(Comparator.comparing(Detail::getId))
                .collect(Collectors.toList());
    }


    public List<Process> getSchedule() {
        return this.processes;
    }

    public void generateSchedule() {

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
            fitness = new FitnessCalculator().getFitness(this);
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
        return machines;
    }

    public List<Detail> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return details.stream()
                .map(detail -> getJobsForDetail(detail.getId()))
                .map(processes1 -> processes1.stream().findFirst().orElse(null).getDetail().getId() + " " +
                        processes1.stream().map(process -> process.getStartTime() + ":" + process.getOperationTime())
                                .collect(joining(" "))
                )
                .collect(joining(System.lineSeparator()));
    }

    private String processListToString(Map.Entry<Detail, List<Process>> detailListEntry) {
        return detailListEntry
                .getValue()
                .stream()
                .map(process -> process.getStartTime() + ":" + process.getOperationTime())
                .collect(joining(" "));
    }

    public List<Process> getJobsForDetail(Integer id) {

        return processes
                .stream()
                .filter(process -> process.getDetail().getId().equals(id))
                .collect(toList());
    }

    public Map<Detail, List<Process>> getDetailSchedule() {
        return processes
                .stream()
                .collect(groupingBy(Process::getDetail));
    }

    public Map<Machine, List<Process>> getMachineSchedule() {
        return processes
                .stream()
                .collect(groupingBy(Process::getMachine));
    }


    public boolean isOverlapping() {
        return detailsAreOverlapping() || machinesAreOverlapping();
    }

    private boolean machinesAreOverlapping() {
        boolean isOverlapping = false;
        for (Map.Entry<Machine, List<Process>> machineListEntry : getMachineSchedule().entrySet()) {
            List<Process> processes = machineListEntry.getValue().stream().sorted(Comparator.comparing(o -> o.getDetail().getId())).collect(toList());
            isOverlapping = isAnyProcessOverlapping(processes);
        }
        return isOverlapping;
    }

    private boolean detailsAreOverlapping() {
        boolean isOverlapping = false;
        for (Detail detail : getDetails()) {
            List<Process> jobsForDetail = getJobsForDetail(detail.getId()).stream().sorted(Comparator.comparing(o -> o.getMachine().getId())).collect(toList());
            isOverlapping = isAnyProcessOverlapping(jobsForDetail);
        }
        return isOverlapping;
    }

    private boolean isAnyProcessOverlapping(List<Process> processes) {
        boolean isOverlapping = false;
        for (int i = 0; i < processes.size() - 1; i++) {
            Process process = processes.get(i);
            Process nextProcess = processes.get(i + 1);
            if (process.getStartTime() + process.getOperationTime() > nextProcess.getStartTime()) {
                isOverlapping = true;
            }
        }
        return isOverlapping;
    }
}
