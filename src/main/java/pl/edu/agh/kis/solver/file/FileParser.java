package pl.edu.agh.kis.solver.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileParser implements Parser
{
    @Override
    public List<String> getFileLines(String fileName) throws IOException
    {
        return Files.readAllLines(Paths.get(fileName));
    }

}
