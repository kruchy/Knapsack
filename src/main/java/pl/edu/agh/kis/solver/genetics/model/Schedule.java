package pl.edu.agh.kis.solver.genetics.model;

import pl.edu.agh.kis.solver.genetics.FitnessCalculator;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static pl.edu.agh.kis.solver.genetics.model.Genotype.randomGenotype;

public class Schedule {

    public static int[] values;
    public static int[] weights;
    private float fitness = 0;
    public Integer id;
    private List<Detail> details;
    private List<Machine> machines;
    private List<DetailProcessQueue> processQueues;
    private List<Process> processes;

    private Genotype genotype;

    @Deprecated
    public Schedule(List<Detail> details, List<Machine> machines, List<DetailProcessQueue> processQueues) {
        this.details = details;
        this.machines = machines;
        this.processQueues = processQueues;

        this.processes = processes.stream()
                .sorted(comparing(p -> ((Process) p).getMachine().getId())
                        .thenComparing(comparing(o -> ((Process) o).getStartTime())))
                .collect(toList());
    }


    public Genotype getGenotype() {
        return genotype;
    }

    public Schedule() {
    }

    public Schedule(List<DetailProcessQueue> loadedProcesses, Genotype genotype) {

        this.machines = loadedProcesses
                .stream()
                .map(DetailProcessQueue::getMachines)
                .flatMap(Collection::stream)
                .distinct()
                .collect(toList());

        this.details = loadedProcesses.stream()
                .map(DetailProcessQueue::getDetail)
                .distinct()
                .collect(toList());
        this.processQueues = loadedProcesses;
        this.genotype = genotype.isValid() ? genotype : randomGenotype(processQueues);
    }

    public Schedule(List<DetailProcessQueue> processQueues) {
        this(processQueues, randomGenotype(processQueues));
    }


    private List<Process> updateStartTimes(List<Process> loadedProcesses) {
        return details.stream().map(Detail::getId)
                .map(integer -> loadedProcesses.stream().filter(process -> process.getDetail().getId().equals(integer)).collect(toList()))
                .map(this::calculateStartTimes)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private List<Process> calculateStartTimes(List<Process> processes1) {
        return Stream.of(Collections.singletonList(processes1.get(0)), processes1.subList(1, processes1.size())
                .stream()
                .map(process -> createProcessWithCumulativeStartTime(processes1, process))
                .collect(toList()))
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private Process createProcessWithCumulativeStartTime(List<Process> processes1, Process process) {
        return new Process(process.getMachine(), process.getDetail(), process.getOperationTime(), cumulativeStartTime(processes1, process));
    }

    private int cumulativeStartTime(List<Process> processes1, Process process) {
        int indexOf = processes1.indexOf(process);
        return indexOf > 0 ? processes1.get(indexOf - 1).getStartTime() + processes1.get(indexOf - 1).getOperationTime() : 0;
    }


    public List<DetailProcessQueue> getSchedule() {
        return this.processQueues;
    }


    public float getFitness() {
        if (fitness == 0) {
            fitness = new FitnessCalculator().getFitness(this);
        }
        return fitness;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public List<Process> getJobsForDetail(Integer id) {

//        return processQueues.stream().filter(processQueue -> processQueue.getDetail().getId().equals(id)).map(processQueue -> processQueue.);
        return null;
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
            List<Process> processes = machineListEntry.getValue().stream().sorted(comparing(o -> o.getDetail().getId())).collect(toList());
            isOverlapping = isAnyProcessOverlapping(processes);
        }
        return isOverlapping;
    }

    private boolean detailsAreOverlapping() {
        boolean isOverlapping = false;
        for (Detail detail : getDetails()) {
            List<Process> jobsForDetail = getJobsForDetail(detail.getId()).stream().sorted(comparing(o -> o.getMachine().getId())).collect(toList());
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

    @Override
    public String toString() {

        List<DetailProcessQueue> collect = processQueues.stream().map(processQueue -> new DetailProcessQueue(processQueue.getDetail(), processQueue.getProcesses().stream().collect(toList()))).collect(toList());
        ArrayList<StringBuilder> lines = new ArrayList<>();
        for (DetailProcessQueue detailProcessQueue : collect) {
            lines.add(new StringBuilder(detailProcessQueue.getDetail().getDescription() + " "));
        }
        for (int i : genotype.genes) {
            Process process = collect.get(i).getNext();
            lines
                    .get(i).append("|").append(process.getMachine().getId()).append(IntStream.rangeClosed(0, process.getOperationTime()).boxed()
                    .map(integer -> "-")
                    .collect(joining()))
                    .append("|  ");
            for (StringBuilder line : lines) {
                if (lines.indexOf(line) != i) {
                    line.append(IntStream.rangeClosed(0, process.getOperationTime() + 4).boxed().map(integer -> " ").collect(joining()));
                }
            }
        }

        return /*machines.stream().map(machine -> machine.getId() + ":" + machine.getDescription()).collect(joining("  ")) +
                System.lineSeparator() + */
                lines.stream().collect(joining(System.lineSeparator()));
//    return  processes.stream().map(process -> "Detail" + process.getDetail().getId()+" Machine"+ process.getMachine().getId() + "-- "+process.getStartTime()+":"+process.getOperationTime()).collect(joining(" "));
    }
}
