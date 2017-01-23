package pl.edu.agh.kis.solver;

import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.FitnessCalculator;
import pl.edu.agh.kis.solver.genetics.SchedulePopulation;
import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;
import pl.edu.agh.kis.solver.genetics.model.Schedule;
import pl.edu.agh.kis.solver.loader.DetailLoader;
import pl.edu.agh.kis.solver.loader.DetailProcessLoader;
import pl.edu.agh.kis.solver.loader.MachineLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class SimpleMain {
    public static void main(String[] args) throws NotValidInput {
        DetailProcessLoader detailProcessLoader = new DetailProcessLoader();
        DetailLoader detailLoader = new DetailLoader();
        MachineLoader machineLoader = new MachineLoader();
        FileParser fileParser = new FileParser();
        List<String> machines;
        List<String> processes;
        List<String> details;
        try {
            machines = fileParser.loadFromResources("machines");
            processes = fileParser.loadFromResources("processes");
            details = fileParser.loadFromResources("details");
        } catch (IOException | URISyntaxException e) {
            throw new NotValidInput("Invalid resource files!");
        }

        List<DetailProcessQueue> loadedProcesses = detailProcessLoader.loadFromInput(processes);
        SchedulePopulation population = new SchedulePopulation(loadedProcesses, 15);
        int i = 100;
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Schedule fittest = population.selectFittest(fitnessCalculator);
        for (int a = 0; a < i /*&&  fittest.isOverlapping()*/; a++) {
            population = new SchedulePopulation(population);
            fittest = population.selectFittest(fitnessCalculator);
            System.out.println();
            System.out.println("Step " + a);
            System.out.println(machines.stream().map(s -> machines.indexOf(s) + " " + s).collect(joining(" ")));
            System.out.println(fittest);
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println(fittest);


    }

}
