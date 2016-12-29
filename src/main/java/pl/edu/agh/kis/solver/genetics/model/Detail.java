package pl.edu.agh.kis.solver.genetics.model;

import java.util.Objects;

public class Detail {


    final private Integer id;
    final   private String desctiption;

    Detail(int id) {
        this.id = id;
        this.desctiption = "Detail" + id;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        return Objects.equals(getId(), detail.getId()) && getDesctiption().equals(detail.getDesctiption());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDesctiption().hashCode();
        return result;
    }
}
