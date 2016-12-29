package pl.edu.agh.kis.solver.genetics.model;

import java.util.List;

public class Detail
{

    private static List<Detail> details;
    final private int id;
    final private String desctiption;

    Detail(int id)
    {
        this.id = id;
        this.desctiption = "Detail" + id;
    }

    public String getDesctiption()
    {
        return desctiption;
    }

    public int getId()
    {
        return id;
    }

    public static List<Detail> getDetails()
    {
        return details;
    }

    public static Detail createDetail(int id)
    {
        Detail detail = new Detail(id);
        details.add(detail);
        return detail;
    }
}
