package pl.edu.agh.kis.solver.genetics.model;

public class Process {

    Machine machine;
    Detail detail;
    int operationTime;

    public Process(Machine machine, Detail detail) {
        this.machine = machine;
        this.detail = detail;
    }



    public int getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(int operationTime) {
        this.operationTime = operationTime;
    }


}
