package pl.edu.agh.kis.solver.genetics.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
        assertThat(schedule.getSchedule(), hasSize(0));
    }

    @Test
    public void generateZeroSchedule_shouldBe5() throws Exception {
        Schedule.defaultDetailNumber = 5;
        schedule.generateZeroSchedule();

        assertThat(schedule.getSchedule(), is(not(nullValue())));
        assertThat(schedule.getSchedule(), hasSize(5));

    }
}