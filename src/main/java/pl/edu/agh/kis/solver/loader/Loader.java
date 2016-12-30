package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.NotValidInput;

import java.util.List;

public interface Loader<T>
{
    List<T> loadFromInput(List<String> input) throws NotValidInput;
}
