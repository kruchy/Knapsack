package pl.edu.agh.kis.solver.genetics.model;

public class Machine {
    String description;
    int id;

    public Machine(int id) {
        this.description = Machine.class + " " + id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;

        if (getId() != machine.getId()) return false;
        return getDescription().equals(machine.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getDescription().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
