package pl.edu.agh.kis.solver.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileParser implements Parser {
    @Override
    public List<String> getFileLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    @Override
    public List<String> loadFromResources(String fileName) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String path = Paths.get(classloader.getResource(fileName).toURI()).toString();
        return getFileLines(path);
    }


}
