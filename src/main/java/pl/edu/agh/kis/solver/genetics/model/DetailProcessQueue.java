package pl.edu.agh.kis.solver.genetics.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetailProcessQueue {

    private final Queue<Process> processes;
    private final Detail detail;

    public DetailProcessQueue(List<Process> processes, Detail detail) {
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

}
