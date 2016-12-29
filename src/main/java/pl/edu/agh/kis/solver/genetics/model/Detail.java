package pl.edu.agh.kis.solver.genetics.model;

public class Detail {


 final   private int id;
    final   private String desctiption;

    Detail(int id) {
        this.id = id;
        this.desctiption = "Detail" + id;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        if (getId() != detail.getId()) return false;
        return getDesctiption().equals(detail.getDesctiption());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDesctiption().hashCode();
        return result;
    }
}
