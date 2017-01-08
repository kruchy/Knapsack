package pl.edu.agh.kis.solver;

import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.FitnessCalculator;
import pl.edu.agh.kis.solver.genetics.SchedulePopulation;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;
import pl.edu.agh.kis.solver.loader.DetailLoader;
import pl.edu.agh.kis.solver.loader.MachineLoader;
import pl.edu.agh.kis.solver.loader.ProcessLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleMain {
    public static void main(String[] args) throws NotValidInput {
        ProcessLoader processLoader = new ProcessLoader();
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

        List<Process> loadedProcesses = processLoader.loadFromInput(processes);
        List<Detail> loadedDetails = detailLoader.loadFromInput(details);
        List<Machine> loadedMachines = machineLoader.loadFromInput(machines);

        List<Schedule> schedules = IntStream.rangeClosed(1, 10).boxed().map(integer -> new Schedule(loadedDetails, loadedMachines, loadedProcesses)).collect(Collectors.toList());
        SchedulePopulation population = new SchedulePopulation(schedules);
//        int i = scanner.nextInt();
        int i = 100;
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Schedule fittest = population.selectFittest(fitnessCalculator);
        for (int a = 0; /*a < i && */ fittest.isOverlapping(); a++) {
            population = new SchedulePopulation(population);
            fittest = population.selectFittest(fitnessCalculator);
            int fitness = fitnessCalculator.getFitness(fittest);
//            System.out.println("ITERATING " + fitness);
//            System.out.println(fittest);
//            System.out.println("DONE ITERATING" );
        }

        System.out.println(fittest.toString());

    }

}
