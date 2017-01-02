package pl.edu.agh.kis.solver.genetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;
import pl.edu.agh.kis.solver.loader.ProcessLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FitnessCalculatorTest {
    public static final List<String> PROCESSES = Arrays.asList("0,1 1,2 4,3", "1,2 3,3 8,1", "5,2 7,4 11,1");
    FitnessCalculator fitnessCalculator = new FitnessCalculator();

    @Test
    public void shouldCalculateDelays() throws Exception, NotValidInput {

        List<Process> loadedProcesses = new ProcessLoader().loadWithStartTime(PROCESSES);
        Map<Detail, List<Process>> jobsForDetails = loadedProcesses.stream().collect(groupingBy(Process::getDetail));

        long delaysFor0 = fitnessCalculator.getDelays(jobsForDetails.get(new Detail(0)));
        long delaysFor1 = fitnessCalculator.getDelays(jobsForDetails.get(new Detail(1)));
        long delaysFor2 = fitnessCalculator.getDelays(jobsForDetails.get(new Detail(2)));
        assertThat(delaysFor0, is(equalTo(1L)));
        assertThat(delaysFor1, is(equalTo(2L)));
        assertThat(delaysFor2, is(equalTo(0L)));
    }

    @Test
    public void shouldCalculateDelaysForMachines() throws Exception {
        List<Process> loadedProcesses = new ProcessLoader().loadWithStartTime(PROCESSES);
        Schedule schedule = new Schedule(loadedProcesses);

        int delaysForMachines = new FitnessCalculator().getDelaysForMachines(schedule);

        assertThat(delaysForMachines, is(equalTo(6)));
    }

    @Test
    public void shouldCalculatePenalties() throws Exception {
        List<Process> loadedProcesses = new ProcessLoader().loadWithStartTime(PROCESSES);
        Schedule schedule = new Schedule(loadedProcesses);

        int delaysForMachines = new FitnessCalculator().getPenaltiesForDetails(schedule);

        assertThat(delaysForMachines, is(equalTo(0)));
    }


    @Test
    public void shouldCalculateDelaysForDetail() throws Exception {
        List<Process> loadedProcesses = new ProcessLoader().loadWithStartTime(PROCESSES);
        Schedule schedule = new Schedule(loadedProcesses);

        int delaysForMachines = new FitnessCalculator().getDelaysForDetails(schedule);

        assertThat(delaysForMachines, is(equalTo(3)));

    }

    @Test
    public void shouldCalculateFitness() throws Exception {
        List<Process> loadedProcesses = new ProcessLoader().loadWithStartTime(PROCESSES);
        Schedule schedule = new Schedule(loadedProcesses);

        int fitness = new FitnessCalculator().getFitness(schedule);

        assertThat(fitness, is(equalTo(9)));

    }


}