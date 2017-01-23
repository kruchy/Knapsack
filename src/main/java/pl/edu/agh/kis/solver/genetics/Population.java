package pl.edu.agh.kis.solver.genetics;


import pl.edu.agh.kis.solver.genetics.model.Schedule;

public interface Population {

    void generatePopulation();

    Schedule mutate(Schedule schedule, int first, int second);

    Schedule selectFittest(FitnessCalculator calculator);


}
