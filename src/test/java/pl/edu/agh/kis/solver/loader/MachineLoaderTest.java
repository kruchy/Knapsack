package pl.edu.agh.kis.solver.loader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.Machine;

@RunWith(MockitoJUnitRunner.class)
public class MachineLoaderTest
{
    @Mock
    private FileParser fileParser;

    private MachineLoader machineLoader = new MachineLoader();

    @Test
    public void shouldLoad3Machines() throws IOException
    {
        String[] detailsFile = { "Frezowanie", "Szlifowanie", "Pakowanie" };
        doReturn(new ArrayList<>(Arrays.asList(detailsFile))).when(fileParser).getFileLines(any(String.class));

        List<Machine> machines = machineLoader.loadFromInput(fileParser.getFileLines("aa"));

        assertThat(machines, hasSize(3));
        assertThat(machines.get(0).getDescription(), is(equalTo(detailsFile[0])));
    }

}
