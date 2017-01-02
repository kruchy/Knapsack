package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DetailHolder {


    public List<Process> getProcesses() {
        return processes;
    }

    private final List<Process> processes;

    public DetailHolder(Integer id, String[] processingTimes) {
        Detail detail = new Detail(id);

        List<String> temp = Arrays.asList(processingTimes);
        processes = temp
                .stream()
                .map(s ->
                        new Process(new Machine(temp.indexOf(s)), detail, Integer.valueOf(s), 0)
                ).collect(toList());
    }
}
