package pl.edu.agh.kis.solver.loader;

import org.junit.Test;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;
import pl.edu.agh.kis.solver.genetics.model.Machine;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class LoadingResourcesTest {


    private DetailLoader detailLoader = new DetailLoader();
    private DetailProcessLoader detailProcessLoader = new DetailProcessLoader();
    private MachineLoader machineLoader = new MachineLoader();

    @Test
    public void shouldLoadDetails() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String path = Paths.get(classloader.getResource("details").toURI()).toString();
        List<String> details = new FileParser().getFileLines(path);
        List<Detail> loadedDetails = detailLoader.loadFromInput(details);

        assertThat(loadedDetails, hasSize(3));
    }

    @Test
    public void shouldLoadMachines() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String path = Paths.get(classloader.getResource("machines").toURI()).toString();
        List<String> machines = new FileParser().getFileLines(path);
        List<Machine> loadedMachines = machineLoader.loadFromInput(machines);

        assertThat(loadedMachines, hasSize(3));
    }

    @Test
    public void shouldLoadProcesses() throws Exception, NotValidInput {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String path = Paths.get(classloader.getResource("processes").toURI()).toString();
        List<String> processes = new FileParser().getFileLines(path);
        List<DetailProcessQueue> loadedProcesses = detailProcessLoader.loadFromInput(processes);

        assertThat(loadedProcesses, hasSize(3));
        assertThat(loadedProcesses.stream().map(DetailProcessQueue::getProcesses).flatMap(Collection::stream).distinct().collect(toList()).size(), is(equalTo(9)));
    }
}
