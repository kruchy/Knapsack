package pl.edu.agh.kis.solver.genetics.model;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest
{

    Schedule schedule;

    @Before
    public void setUp() throws Exception
    {
        schedule = new Schedule();
    }

    @Test
    public void generateEmptySchedule_shouldHave0Processes() throws Exception
    {
        schedule.generateSchedule();
        assertThat(schedule.getProcesses(), is(not(nullValue())));
//        assertThat(schedule.getProcesses(), hasSize(0));
    }

    @Test
    public void generateZeroSchedule_shouldBe5() throws Exception
    {
        Schedule.defaultDetailNumber = 5;
        schedule.generateZeroSchedule();

        assertThat(schedule.getProcesses(), is(not(nullValue())));
//        assertThat(schedule.getProcesses(), hasSize(5));

    }

    @Test
    public void name() throws Exception
    {
        List<Process> processes = IntStream.range(1, 4).mapToObj(value -> new Process(MachineSupplier.getMachineForId(1), new Detail(value), 1)).collect(toList());
//        schedule = new Schedule(processes);
    }

}
