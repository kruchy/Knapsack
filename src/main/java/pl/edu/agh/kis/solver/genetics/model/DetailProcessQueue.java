package pl.edu.agh.kis.solver.genetics.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.stream.Collectors.toList;

public class DetailProcessQueue {

    private final Queue<Process> processes;
    private final Detail detail;

    public DetailProcessQueue(Detail detail, List<Process> processes) {
        this.processes = new LinkedList<>();
        for (Process process : processes) {
            this.processes.offer(process);
        }
        this.detail = detail;
    }

    public int size() {
        return processes.size();
    }

    public Detail getDetail() {
        return detail;
    }

    public Process getNext() {
        return processes.poll();
    }

    public List<Machine> getMachines() {
        return processes.stream().map(Process::getMachine).collect(toList());
    }

    public List<Process> getProcesses() {
        return processes.stream().collect(toList());
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

}
