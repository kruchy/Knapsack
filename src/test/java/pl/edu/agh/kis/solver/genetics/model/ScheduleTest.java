package pl.edu.agh.kis.solver.genetics.model;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest
{

    Schedule schedule;

    @Before
    public void setUp()
    {
        schedule = new Schedule();
    }

    @Test
    public void name()
    {
        schedule.generateSchedule();
        assertThat(schedule.getSchedule(), is(not(nullValue())));
    }

    @Test
    public void generateZeroSchedule_shouldHave3Machines()
    {

        List<Process> processes = fromMatrix();
        List<String> stringStream = fromMatrix()
                .stream()
                .map(process -> process.getDetail().getDescription() + "  on" + process.getMachine().getDescription())
                .collect(toList());
        schedule.generateZeroSchedule(processes);

        assertThat(schedule.getSchedule(), is(not(nullValue())));
        assertThat(schedule.getMachines(), hasSize(3));
        assertThat(schedule.getDetails(), hasSize(3));
    }

    @Test
    public void shouldReturnSortedJobList()
    {
        schedule.generateZeroSchedule(fromMatrix());

        List<Process> jobs = schedule.getJobsForDetail(1);
        assertThat(jobs, is(sorted(comparing(Process::getOperationTime).reversed())));
    }

    @Test
    public void shouldReturn() throws Exception
    {

    }

    private Matcher<List<Process>> sorted(final Comparator<Process> comparing)
    {
        return new TypeSafeMatcher<List<Process>>()
        {
            @Override
            protected boolean matchesSafely(List<Process> processes)
            {
                return processes
                        .stream()
                        .sorted(comparing)

                        .collect(toList())
                        .equals(processes.stream().collect(toList()));
            }

            @Override
            public void describeTo(Description description)
            {

            }
        };
    }

    private List<Process> getProcesses()
    {
        return IntStream.rangeClosed(1, 3)
                .boxed()
                .map(i -> IntStream.rangeClosed(1, 3).boxed().collect(toList()))
                .flatMap(Collection::stream)
                .map(i -> new Process(new Machine(i), new Detail(i), 1, i + 5))
                .collect(toList());
    }

    private List<Process> fromMatrix()
    {
        int[][] arr = new int[3][3];
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = (j + 1) * (i + 1);
                processes.add(new Process(new Machine(i), new Detail(j), i * j, i + j));
            }
        }
        return processes;
    }
}
