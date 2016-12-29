package pl.edu.agh.kis.solver.genetics.model;

public class Machine
{
    final String description;
    final int id;

    public Machine(int id)
    {
        this.description = Machine.class + " " + id;
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

}
