package pl.edu.agh.kis.solver.genetics.model;

public class Process {

    final Machine machine;
    final Detail detail;
    final int operationTime;

    public Process(Machine machine, Detail detail, int operationTime) {
        this.machine = machine;
        this.detail = detail;
        this.operationTime = operationTime;
    }

    public Machine getMachine() {
        return machine;
    }

    public Detail getDetail() {
        return detail;
    }

    public int getOperationTime() {
        return operationTime;
    }



}
