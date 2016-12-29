package pl.edu.agh.kis.solver.genetics.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {

    Schedule schedule;

    @Before
    public void setUp() throws Exception {
        schedule = new Schedule();
    }

    @Test
    public void name() throws Exception {
        schedule.generateSchedule();
        assertThat(schedule.getSchedule(), is(not(nullValue())));
    }

    @Test
    public void generateZeroSchedule_shouldHave3Machines() throws Exception {

        List<Process> processes = IntStream.rangeClosed(1, 3).boxed()
                .map(i -> IntStream.rangeClosed(1, 3).boxed().collect(toList()))
                .flatMap(Collection::stream)
                .map(i -> new Process(new Machine(i), new Detail(i), 1)).collect(toList());

        schedule.generateZeroSchedule(processes);

        assertThat(schedule.getSchedule(), is(not(nullValue())));
        assertThat(schedule.getMachines(), hasSize(3));
        assertThat(schedule.getDetails(), hasSize(3));
    }


}