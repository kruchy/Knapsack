package pl.edu.agh.kis.solver.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface Parser
{
    List<String> getFileLines(String fileName) throws IOException;

    List<String> loadFromResources(String fileName) throws IOException, URISyntaxException;
}
