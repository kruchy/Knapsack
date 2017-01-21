package pl.edu.agh.kis.solver.genetics.model;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Process {

    private final Machine machine;
    private final Detail detail;
    private Integer operationTime;

    private Integer startTime;
    private Integer id;

    public Process(Machine machine, Detail detail, int operationTime, int startTime) {
        this.machine = machine;
        this.detail = detail;
        this.operationTime = operationTime;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        if (!getMachine().equals(process.getMachine())) return false;
        if (!getDetail().equals(process.getDetail())) return false;
        if (!getOperationTime().equals(process.getOperationTime())) return false;
        return getId() != null ? getId().equals(process.getId()) : process.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = getMachine().hashCode();
        result = 31 * result + getDetail().hashCode();
        result = 31 * result + getOperationTime().hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
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


    public Integer getId() {
        return id;
    }

    public void setOperationTime(Integer integer) {
        this.operationTime = integer;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "|m" + machine.getId() + ",d" + detail.getId() + IntStream.rangeClosed(0, operationTime).boxed().map(integer -> "-").collect(joining()) + "|";
    }
}
