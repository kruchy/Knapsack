package pl.edu.agh.kis.solver.loader;

import pl.edu.agh.kis.solver.genetics.model.Detail;
import pl.edu.agh.kis.solver.genetics.model.Machine;
import pl.edu.agh.kis.solver.genetics.model.Process;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DetailHolder {


    private final Detail detail;

    public List<Process> getProcesses() {
        return processes;
    }

    public Detail getDetail() {
        return this.detail;
    }

    private final List<Process> processes;

    public DetailHolder(Integer id, String[] processingTimes) {
        detail = new Detail(id);

        List<String> temp = Arrays.asList(processingTimes);
        processes = temp
                .stream()
                .map(s ->
                        new Process(new Machine(temp.indexOf(s)), detail, Integer.valueOf(s), 0)
                ).collect(toList());
    }
}
