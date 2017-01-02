package pl.edu.agh.kis.solver.genetics.model;

public class Process {

    private final Machine machine;
    private final Detail detail;
    private final Integer operationTime;

    final private Integer startTime;

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

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getOperationTime() {
        return operationTime;
    }



}
