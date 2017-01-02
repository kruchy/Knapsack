package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Process> loadWithStartTime(List<String> input) {
        List<Process> processes = new ArrayList<>();
        for (String s : input) {
            int detailNumber = input.indexOf(s);
            Detail detail = new Detail(detailNumber);
            List<String> times = Arrays.asList(s.split("\\s"));
            for (String time : times) {
                String[] split = time.split(",");
                Process process = new Process(new Machine(times.indexOf(time)), detail, Integer.valueOf(split[1]), Integer.valueOf(split[0]));
                processes.add(process);
            }
        }
        return processes;
    }

}
