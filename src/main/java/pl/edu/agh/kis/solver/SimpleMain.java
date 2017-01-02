package pl.edu.agh.kis.solver;

import pl.edu.agh.kis.solver.file.FileParser;
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
        Schedule schedule = new Schedule(loadedDetails, loadedMachines, loadedProcesses);


    }

}
