package pl.edu.agh.kis.solver.loader;

import org.junit.Test;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class LoadingResourcesTest {


    private DetailLoader detailLoader = new DetailLoader();
    private ProcessLoader processLoader = new ProcessLoader();
    private MachineLoader machineLoader = new MachineLoader();

    @Test
    public void shouldLoadDetails() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String platformIndependentPath = Paths.get(classloader.getResource("details").toURI()).toString();
        List<String> details = Files.readAllLines(Paths.get(platformIndependentPath));
        List<Detail> loadedDetails = detailLoader.loadFromInput(details);

        assertThat(loadedDetails, hasSize(3));
    }

    @Test
    public void shouldLoadMachines() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String platformIndependentPath = Paths.get(classloader.getResource("machines").toURI()).toString();
        List<String> machines = Files.readAllLines(Paths.get(platformIndependentPath));
        List<Machine> loadedMachines = machineLoader.loadFromInput(machines);

        assertThat(loadedMachines, hasSize(3));
    }

    @Test
    public void shouldLoadProcesses() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String platformIndependentPath = Paths.get(classloader.getResource("processes").toURI()).toString();
        List<String> processes = Files.readAllLines(Paths.get(platformIndependentPath));
        List<Process> loadedProcesses = processLoader.loadFromInput(processes);

        assertThat(loadedProcesses, hasSize(9));
        assertThat(loadedProcesses.stream().collect(groupingBy(Process::getDetail)).size(), is(equalTo(3)));
    }
}
