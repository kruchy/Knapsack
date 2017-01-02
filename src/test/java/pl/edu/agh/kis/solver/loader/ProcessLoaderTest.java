package pl.edu.agh.kis.solver.loader;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProcessLoaderTest {

    ProcessLoader processLoader = new ProcessLoader();
    String format3 = "%d\t%d\t%d";

    @Mock
    FileParser fileParser;


    @Test
    public void shouldLoad3ProcessesFor3Details() throws Exception, NotValidInput {
        String[] strings = {get3Params(1, 5, 3), get3Params(2, 4, 8), get3Params(2, 7, 10)};
        doReturn(new ArrayList<>(Arrays.asList(strings))).when(fileParser).getFileLines(any(String.class));

        List<Process> processes = processLoader.loadFromInput(fileParser.getFileLines("a"));

        Map<Detail, List<Process>> detailProcess = processes.stream().collect(groupingBy(Process::getDetail));
        assertThat(processes, is(not(nullValue())));
        assertThat(processes, hasSize(9));
        assertThat(detailProcess.size(), is(equalTo(3)));
        assertThat(detailProcess.values().stream().mapToInt(Collection::size).sum(), is(equalTo(9)));


    }

    private String get3Params(int i, int i2, int i3) {
        return String.format(format3, i, i2, i3);
    }
}