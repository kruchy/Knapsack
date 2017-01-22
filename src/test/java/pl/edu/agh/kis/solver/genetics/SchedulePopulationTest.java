package pl.edu.agh.kis.solver.genetics;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;
import pl.edu.agh.kis.solver.genetics.model.Schedule;
import pl.edu.agh.kis.solver.loader.DetailProcessLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(MockitoJUnitRunner.class)
public class SchedulePopulationTest {


    @Test
    @Ignore
    public void shouldCrossover2SchedulesEqually() throws Exception {
//        List<DetailProcessQueue> detailProcessQueues = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));
//        SchedulePopulation schedulePopulation = new SchedulePopulation(detailProcessQueues, 2);
//        SchedulePopulation mutatedSchedulePopulation = new SchedulePopulation(schedulePopulation);
//
//        boolean anyMatch = mutatedSchedulePopulation.selectFittest(new FitnessCalculator()).getSchedule().stream().map(get).map(Process::getStartTime).anyMatch(integer -> integer != 0);
//        assertThat(anyMatch, is(true));
    }

    @Test
    public void shouldHaveSameProcessList() throws Exception {
        List<DetailProcessQueue> detailProcessQueues = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));
        SchedulePopulation schedulePopulation = new SchedulePopulation(detailProcessQueues, 1);
        SchedulePopulation newSchedulePopulation = new SchedulePopulation(schedulePopulation);

        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Schedule schedule = schedulePopulation.selectFittest(fitnessCalculator);
        Schedule schedule1 = newSchedulePopulation.selectFittest(fitnessCalculator);

        assertThat(schedule.getSchedule().size(), is(equalTo(schedule1.getSchedule().size())));
    }


    @Test
    public void shouldNotChangeProcessListSizeAfter100Mutations() throws IOException, URISyntaxException, NotValidInput {
        List<DetailProcessQueue> processes = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));

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
        List<DetailProcessQueue> processes1 = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));

        SchedulePopulation schedulePopulation = new SchedulePopulation(processes1, 1);

        Schedule schedule1 = new Schedule(processes1);
        Schedule schedule2 = new Schedule(processes1);
        Schedule processes = schedulePopulation.cycleCrossover(schedule1, schedule2, 3);

//        for (int i = 0; i < 3; i++) {
//            Process actual = processes.get(i);
//            Process rightSide = processes1.get(i);
//            assertThat(actual, is(equalTo(rightSide)));
//        }
//        for (int i = 3; i < processes.size(); i++) {
//            Process actual = processes.get(i);
//            Process rightSide = processes1.get(i);
//            assertThat(actual, is(equalTo(rightSide)));
//        }


    }


}