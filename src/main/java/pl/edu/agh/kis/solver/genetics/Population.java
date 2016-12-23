package pl.edu.agh.kis.solver.genetics;


import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.List;

public interface Population {

    void generatePopulation();

    void mutate();

    void crossover(Schedule schedule1, Schedule schedule2);

    List<Schedule> selectFittest(FitnessCalc calculator);


}
