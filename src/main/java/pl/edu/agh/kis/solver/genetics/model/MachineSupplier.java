package pl.edu.agh.kis.solver.genetics.model;

public class MachineSupplier {
    public static  Machine getMachineForId(int id)
    {
        return new Machine(id);
    }
}
