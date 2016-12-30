package pl.edu.agh.kis.solver.loader;

import static java.util.stream.Collectors.toList;

import java.util.List;

import pl.edu.agh.kis.solver.genetics.model.Machine;

public class MachineLoader implements Loader<Machine>
{

    @Override
    public List<Machine> loadFromInput(List<String> input)
    {
        return input
                .stream()
                .map(line -> new Machine(input.indexOf(line) + 1, line))
                .collect(toList());
    }

}
