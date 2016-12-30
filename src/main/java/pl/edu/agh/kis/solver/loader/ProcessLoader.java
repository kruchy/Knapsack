package pl.edu.agh.kis.solver.loader;

import java.util.List;

import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Process;

public class ProcessLoader implements Loader<Process>
{
    @Override
    public List<Process> loadFromInput(List<String> input) throws NotValidInput
    {
        return null;
    }

    @Override
    public boolean isValid(List<String> input)
    {
        return false;
    }
}
