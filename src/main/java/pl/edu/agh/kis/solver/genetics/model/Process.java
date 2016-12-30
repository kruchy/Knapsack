package pl.edu.agh.kis.solver.genetics.model;

public class Process {

    final Machine machine;
    final Detail detail;
    final Integer operationTime;

    final private int startTime;

    public Process(Machine machine, Detail detail, int operationTime, int startTime) {
        this.machine = machine;
        this.detail = detail;
        this.operationTime = operationTime;
        this.startTime = startTime;
    }

    public Machine getMachine() {
        return machine;
    }

    public Detail getDetail() {
        return detail;
    }

    public int getStartTime() {
        return startTime;
    }

    public Integer getOperationTime() {
        return operationTime;
    }



}
