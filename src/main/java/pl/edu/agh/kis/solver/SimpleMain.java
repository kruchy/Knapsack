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
//        List<Detail> loadedDetails = detailLoader.loadFromInput(details);
//        List<Machine> loadedMachines = machineLoader.loadFromInput(machines);

//        List<Schedule> schedules = IntStream.rangeClosed(1, 10).boxed().map(integer -> new Schedule(loadedDetails, loadedMachines, loadedProcesses)).collect(Collectors.toList());
        SchedulePopulation population = new SchedulePopulation(loadedProcesses, 15);
//        int i = scanner.nextInt();
        int i = 15;
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Schedule fittest = population.selectFittest(fitnessCalculator);
        for (int a = 0; a < i /*&&  fittest.isOverlapping()*/; a++) {
            population = new SchedulePopulation(population);
            fittest = population.selectFittest(fitnessCalculator);
            int fitness = fitnessCalculator.getFitness(fittest);
            System.out.println(fittest.toString());
            System.out.println("-------------------------");
            //            System.out.println("ITERATING " + fitness);
//            System.out.println(fittest);
//            System.out.println("DONE ITERATING" );
        }

        System.out.println(fittest.toString());


    }

}
