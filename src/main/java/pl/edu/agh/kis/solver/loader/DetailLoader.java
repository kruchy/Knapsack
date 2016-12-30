package pl.edu.agh.kis.solver.loader;

import static java.util.stream.Collectors.toList;

import java.util.List;

import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Detail;

public class DetailLoader implements Loader<Detail>,InputValidator
{
    @Override
    public List<Detail> loadFromInput(List<String> input) throws NotValidInput
    {
        if (!isValid(input))
        {
            throw new NotValidInput("Not valid details file!");
        }
        return input
                .stream()
                .map(line -> new Detail(input.indexOf(line) + 1, line))
                .collect(toList());
    }

    @Override
    public boolean isValid(List<String> input)
    {
        long count = input
                .stream()
                .map(s -> s.split("\t"))
                .map(strings -> strings.length)
                .distinct()
                .count();
        return count == 1;
    }
}
