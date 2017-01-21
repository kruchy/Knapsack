package pl.edu.agh.kis.solver.genetics;


import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.List;

public interface Population {

    void generatePopulation();

    Schedule mutate(Schedule schedule, int first, int second);

    List<Process> crossover(Schedule schedule1, Schedule schedule2);

    Schedule selectFittest(FitnessCalculator calculator);


}
