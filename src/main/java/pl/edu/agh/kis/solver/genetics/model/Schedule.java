package pl.edu.agh.kis.solver.genetics.model;

import pl.edu.agh.kis.solver.genetics.FitnessCalc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Schedule {

    static int defaultDetailNumber = 4;

    public static int[] values;
    public static int[] weights;
    private float fitness = 0;
    public int id;
    private List<Process> processes;

    public Schedule() {
    }

    public Schedule(int id) {
        this.id = id;
    }

    public List<Process> getSchedule() {
        return this.processes;
    }

    public void generateSchedule() {
        processes = new ArrayList<>();
    }

    public void generateZeroSchedule() {
        Machine machineForId = MachineSupplier.getMachineForId(1);
        processes = IntStream.rangeClosed(1, defaultDetailNumber).boxed()
                .map(i -> new Process(machineForId, new Detail(i)))
                .collect(toList());
    }

    public static void setDefaultDetailNumber(int length) {
        defaultDetailNumber = length;
        values = new int[defaultDetailNumber];
        weights = new int[defaultDetailNumber];
    }


    public void setValues(int[] vals) {
        values = vals;
    }

    public void setWeights(int[] weis) {
        weights = weis;
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
}
