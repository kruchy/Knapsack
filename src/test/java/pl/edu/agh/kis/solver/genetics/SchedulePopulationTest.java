package pl.edu.agh.kis.solver.genetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SchedulePopulationTest {

    @Test
    public void shouldCrossover2SchedulesEqually() throws Exception {
        List<Process> processes = IntStream.rangeClosed(1, 6).boxed().map(integer -> mock(Process.class, RETURNS_DEEP_STUBS)).collect(Collectors.toList());
        SchedulePopulation schedulePopulation = new SchedulePopulation(processes, 2);
        SchedulePopulation mutatedSchedulePopulation = new SchedulePopulation(schedulePopulation);

        boolean anyMatch = mutatedSchedulePopulation.selectFittest(new FitnessCalculator()).getSchedule().stream().map(Process::getStartTime).anyMatch(integer -> integer != 0);
        assertThat(anyMatch, is(true));
    }

    @Test
    public void shouldHaveSameProcessList() throws Exception {
        List<Process> processes = IntStream.rangeClosed(1, 6).boxed().map(integer -> mock(Process.class, RETURNS_DEEP_STUBS)).collect(Collectors.toList());
        SchedulePopulation schedulePopulation = new SchedulePopulation(processes, 1);
        SchedulePopulation newSchedulePopulation = new SchedulePopulation(schedulePopulation);

        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Schedule schedule = schedulePopulation.selectFittest(fitnessCalculator);
        Schedule schedule1 = newSchedulePopulation.selectFittest(fitnessCalculator);

        assertThat(schedule.getSchedule().size(), is(equalTo(schedule1.getSchedule().size())));
    }

    private List<Process> fromMatrix() {
        int[][] arr = new int[3][3];
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (j + 1) * (i + 1);
                processes.add(new Process(new Machine(i), new Detail(j), i * j, i + j));
            }
        }
        return processes;
    }

}