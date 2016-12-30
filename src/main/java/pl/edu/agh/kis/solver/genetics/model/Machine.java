package pl.edu.agh.kis.solver.genetics.model;

import java.util.Objects;

public class Machine
{
    final String description;
    final Integer id;

    public Machine(int id)
    {
        this.description = Machine.class + " " + id;
        this.id = id;
    }

    public Machine(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Machine machine = (Machine) o;

        return Objects.equals(getId(), machine.getId()) && getDescription().equals(machine.getDescription());
    }

    @Override
    public int hashCode()
    {
        int result = getDescription().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
