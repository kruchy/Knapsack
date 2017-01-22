package pl.edu.agh.kis.solver.genetics.model;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.loader.DetailProcessLoader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {

    Schedule schedule;

    @Before
    public void setUp() {
        schedule = new Schedule();
    }


    @Test
    @Ignore
    public void shouldNotBeOverlapping() throws Exception {
        List<String> input = Arrays.asList("0,1 1,2 4,3", "1,2 3,3 8,1", "5,2 7,4 11,1");
        List<DetailProcessQueue> processes = new DetailProcessLoader().loadWithStartTime(input);
        Schedule schedule = new Schedule(processes);

        assertThat(schedule.isOverlapping(), is(false));

    }

    @Test
    @Ignore
    public void shouldBeOverlapping() throws Exception {
        List<String> input = Arrays.asList("3,1 1,2 2,3", "2,4 3,5 2,6", "1,7 8,8 2,9");
        List<DetailProcessQueue> processes = new DetailProcessLoader().loadWithStartTime(input);
        Schedule schedule = new Schedule(processes);

        assertThat(schedule.isOverlapping(), is(true));
    }

    private Matcher<List<Process>> sorted(final Comparator<Process> comparing) {
        return new TypeSafeMatcher<List<Process>>() {
            @Override
            protected boolean matchesSafely(List<Process> processes) {
                return processes
                        .stream()
                        .sorted(comparing)
                        .collect(toList())
                        .equals(processes.stream().collect(toList()));
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    @Test
    public void shouldNotChangeProcessSize() throws Exception, NotValidInput {
        List<DetailProcessQueue> processes = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));
        Schedule schedule = new Schedule(processes);

        assertThat(schedule.getSchedule(), hasSize(processes.size()));
    }

    @Test
    public void shouldGenerateRandomGenotype() throws Exception {
        List<DetailProcessQueue> processes = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));

        int[] ints = Genotype.randomGenotype(processes).genes;

        assertThat(Arrays.stream(ints).distinct().count(), is(equalTo((long) processes.size())));

        for (int i = 0; i < processes.size(); i++) {
            int temp = i;
            assertThat(Arrays.stream(ints).filter(value -> value == temp).count(), is(equalTo((long) processes.stream().map(DetailProcessQueue::getProcesses).mapToInt(List::size).sum() / processes.size())));
        }
    }
}
