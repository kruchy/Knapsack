package pl.edu.agh.kis.solver.loader;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DetailProcessLoaderTest {

    DetailProcessLoader detailProcessLoader = new DetailProcessLoader();
    String format3 = "%d\t%d\t%d";

    @Mock
    FileParser fileParser;


    @Test
    public void shouldLoad3ProcessesFor3Details() throws Exception, NotValidInput {
        String[] strings = {get3Params(1, 5, 3), get3Params(2, 4, 8), get3Params(2, 7, 10)};
        doReturn(new ArrayList<>(Arrays.asList(strings))).when(fileParser).getFileLines(any(String.class));

        List<DetailProcessQueue> processes = detailProcessLoader.loadFromInput(fileParser.getFileLines("a"));

//        Map<Detail, List<Process>> detailProcess = processes.stream().collect(groupingBy(Process::getDetail));
        assertThat(processes, is(not(nullValue())));
        assertThat(processes, hasSize(3));
        assertThat(processes.stream().mapToInt(DetailProcessQueue::size).sum(), is(equalTo(9)));


    }

    private String get3Params(int i, int i2, int i3) {
        return String.format(format3, i, i2, i3);
    }
}