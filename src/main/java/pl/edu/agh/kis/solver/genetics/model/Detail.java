package pl.edu.agh.kis.solver.genetics.model;

public class Detail {


    private int id;
    private String desctiption;

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

    public void setId(int id) {
        this.id = id;
    }
}
