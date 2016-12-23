package pl.edu.agh.kis.solver.genetics;


import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class SchedulePopulation implements Population {
    private List<Schedule> schedules;
    private List<Process> processes;

    public SchedulePopulation(List<Process> processes) {
        this.processes = processes;
        this.schedules = new ArrayList<Schedule>();
    }

    public void generatePopulation() {

    }

    public void mutate() {

    }

    public void crossover(Schedule schedule1, Schedule schedule2) {

    }

    public List<Schedule> selectFittest(FitnessCalc calculator) {
        return null;
    }
}
