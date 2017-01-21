package pl.edu.agh.kis.solver.genetics;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;
import pl.edu.agh.kis.solver.loader.ProcessLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchedulePopulationTest {


    @Test
    @Ignore
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


    @Test
    public void shouldNotChangeProcessListSizeAfter100Mutations() throws IOException, URISyntaxException, NotValidInput {
        List<Process> processes = new ProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));

        SchedulePopulation schedulePopulation = new SchedulePopulation(processes, 5);
        for (int i = 0; i < 100; i++) {
            schedulePopulation = new SchedulePopulation(schedulePopulation);
            for (Schedule schedule : schedulePopulation.getSchedules()) {
                assertThat(schedule.getSchedule(), hasSize(processes.size()));
            }
        }

    }

    @Test
    public void shouldCrossoverWithOffset() throws IOException, URISyntaxException, NotValidInput {
        List<Process> processes1 = new ProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));
//        List<Process> processes2 = IntStream.rangeClosed(7, 12).boxed().map(this::mockProcess).collect(Collectors.toList());

        SchedulePopulation schedulePopulation = new SchedulePopulation(processes1, 1);

        Schedule schedule1 = new Schedule(processes1);
        Schedule schedule2 = new Schedule(processes1);
        List<Process> processes = schedulePopulation.cycleCrossover(schedule1, schedule2, 3);

        for (int i = 0; i < 3; i++) {
            Process actual = processes.get(i);
            Process rightSide = processes1.get(i);
            assertThat(actual, is(equalTo(rightSide)));
        }
        for (int i = 3; i < processes.size(); i++) {
            Process actual = processes.get(i);
            Process rightSide = processes1.get(i);
            assertThat(actual, is(equalTo(rightSide)));
        }


    }

    private Process mockProcess(Integer integer) {
        Process mock = mock(Process.class, RETURNS_DEEP_STUBS);
        when(mock.getId()).thenReturn(integer);
        return mock;
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