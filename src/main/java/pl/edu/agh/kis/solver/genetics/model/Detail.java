package pl.edu.agh.kis.solver.genetics.model;

import java.util.Objects;

public class Detail
{

    final private Integer id;

    final private Integer minStartTime;

    final private Integer maxFinishTime;
    final private String description;

    Detail(int id)
    {
        this.id = id;
        this.description = "Detail" + id;
        this.minStartTime = 0;
        this.maxFinishTime = 999;
    }

    public Detail(Integer id, Integer minStartTime, Integer maxFinishTime)
    {
        this.id = id;
        this.minStartTime = minStartTime;
        this.maxFinishTime = maxFinishTime;
        this.description = "Detail" + id;
    }

    public Detail(Integer id, String description)
    {
        this.id = id;
        this.description = description;
        this.minStartTime = 0;
        this.maxFinishTime = 999;
    }

    public String getDescription()
    {
        return description;
    }

    public Integer getMinStartTime()
    {
        return minStartTime;
    }

    public Integer getMaxFinishTime()
    {
        return maxFinishTime;
    }

    public Integer getId()
    {
        return id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Detail detail = (Detail) o;

        return Objects.equals(getId(), detail.getId()) && getDescription().equals(detail.getDescription());
    }

    @Override
    public int hashCode()
    {
        int result = getId();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
