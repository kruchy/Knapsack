package pl.edu.agh.kis.solver.genetics.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Schedule
{

    static int defaultDetailNumber = 4;

    public static int[] values;
    public static int[] weights;
    private float fitness = 0;
    public int id;
    private Map<Machine, List<Process>> machinesProcesses;

    public Schedule()
    {
    }

    public Schedule(int id)
    {
        this.id = id;
    }

    public Schedule(Map<Machine, List<Process>> machinesProcesses)
    {
        this.machinesProcesses =machinesProcesses;
    }

    public Map<Machine, List<Process>> getProcesses()
    {
        return this.machinesProcesses;
    }

    public void generateSchedule()
    {
        machinesProcesses = new HashMap<>();
    }

    public void generateZeroSchedule()
    {
        Machine machineForId = MachineSupplier.getMachineForId(1);
//        processes = IntStream.rangeClosed(1, defaultDetailNumber)
//                .boxed()
//                .map(i -> new Process(machineForId, new Detail(i), 1))
//                .collect(toList());
    }

    public static void setDefaultDetailNumber(int length)
    {
        defaultDetailNumber = length;
        values = new int[defaultDetailNumber];
        weights = new int[defaultDetailNumber];
    }

    public void setValues(int[] vals)
    {
        values = vals;
    }

    public void setWeights(int[] weis)
    {
        weights = weis;
    }

    public float getFitness(int id)
    {
        if (fitness == 0)
        {
            // fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    public static Schedule randomSchedule()
    {
        return new Schedule();
    }


}
