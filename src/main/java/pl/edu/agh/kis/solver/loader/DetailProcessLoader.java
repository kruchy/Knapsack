package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.NotValidInput;
import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DetailProcessLoader implements Loader<DetailProcessQueue>, InputValidator {
    @Override
    public List<DetailProcessQueue> loadFromInput(List<String> input) throws NotValidInput {

        return input.stream()
                .map(s -> new DetailHolder(input.indexOf(s), s.split("\\s")))
                .map(detailHolder -> new DetailProcessQueue(detailHolder.getDetail(), detailHolder.getProcesses()))
                .collect(toList());
    }

    @Override
    public boolean isValid(List<String> input) {
        return false;
    }

    public List<DetailProcessQueue> loadWithStartTime(List<String> input) {
        List<DetailProcessQueue> detailProcessQueues = new ArrayList<>();
        for (String s : input) {
            int detailNumber = input.indexOf(s);
            List<Process> processes = new ArrayList<>();
            Detail detail = new Detail(detailNumber);
            List<String> times = Arrays.asList(s.split("\\s"));
            for (String time : times) {
                String[] split = time.split(",");
                Process process = new Process(new Machine(times.indexOf(time)), detail, Integer.valueOf(split[1]), Integer.valueOf(split[0]));
                processes.add(process);
            }
            detailProcessQueues.add(new DetailProcessQueue(detail, processes));
        }
        return detailProcessQueues;
    }

}
