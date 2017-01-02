package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProcessLoader implements Loader<Process>, InputValidator {
    @Override
    public List<Process> loadFromInput(List<String> input) throws NotValidInput {

        return input.stream()
                .map(s -> new DetailHolder(input.indexOf(s), s.split("\\s")))
                .map(DetailHolder::getProcesses)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    @Override
    public boolean isValid(List<String> input) {
        return false;
    }
}
