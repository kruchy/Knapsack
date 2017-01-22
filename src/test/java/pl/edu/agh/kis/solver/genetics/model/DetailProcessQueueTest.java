package pl.edu.agh.kis.solver.genetics.model;

import org.junit.Test;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.loader.DetailProcessLoader;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

public class DetailProcessQueueTest {

    @Test
    public void shouldRemoveAfterRetrieval() throws Exception {
        List<DetailProcessQueue> testProcesses = new DetailProcessLoader().loadFromInput(new FileParser().loadFromResources("testProcesses"));
        for (DetailProcessQueue testProcess : testProcesses) {
            int before = testProcess.size();
            testProcess.getNext();
            int after = testProcess.size();
            assertThat(before, is(greaterThan(after)));
        }

    }
}