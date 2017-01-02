package pl.edu.agh.kis.solver.loader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.file.FileParser;
import pl.edu.agh.kis.solver.genetics.model.Detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DetailLoaderTest
{

    private DetailLoader detailLoader = new DetailLoader();

    @Mock
    FileParser fileParser;

    @Test
    public void shouldPass_load3DetailsWithoutTimes() throws IOException, NotValidInput
    {
        String[] detailsFile = { "DetailA", "DetailB", "DetailC" };
        doReturn(new ArrayList<>(Arrays.asList(detailsFile))).when(fileParser).getFileLines(any(String.class));

        List<Detail> details = detailLoader.loadFromInput(fileParser.getFileLines("aaa"));

        assertThat(details, hasSize(3));
        assertThat(details.get(0).getId(), is(equalTo(1)));
        assertThat(details.get(0).getDescription(), is(equalTo(detailsFile[0])));
        assertThat(details.get(1).getId(), is(equalTo(2)));
    }

    @Test(expected = NotValidInput.class)
    public void shouldFail_ThrowNotValid() throws Exception, NotValidInput
    {
        String[] detailsFile = { "DetailA", "DetailB\t2\t3\t4", "DetailC\t2\t" };
        doReturn(new ArrayList<>(Arrays.asList(detailsFile))).when(fileParser).getFileLines(any(String.class));

        List<Detail> details = detailLoader.loadFromInput(fileParser.getFileLines("aaa"));
        fail("Should fail because input is not consistent!");
    }

    @Test
    public void shouldLoadDetailsWithTimes() throws IOException
    {
        //TODO Parse with times
        String[] detailsFile = { "DetailA 1 30", "DetailB 5 150", "DetailC 3 100" };
        doReturn(new ArrayList<>(Arrays.asList(detailsFile))).when(fileParser).getFileLines(any(String.class));
    }

}
