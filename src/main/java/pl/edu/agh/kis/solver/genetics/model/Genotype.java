package pl.edu.agh.kis.solver.genetics.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Genotype {

    public int[] genes;

    public Genotype(int[] genes) {
        this.genes = genes;
    }


    public static Genotype randomGenotype(List<DetailProcessQueue> processQueues) {
        List<Integer> collect = processQueues.stream().map(processQueue -> processQueue.getProcesses().stream().map(process -> processQueues.indexOf(processQueue)).collect(toList())).flatMap(Collection::stream).collect(toList());
        Collections.shuffle(collect);
        return new Genotype(collect.stream().mapToInt(i -> i).toArray());
    }

    public boolean isValid() {

        return IntStream.of(genes)
                .mapToObj(o -> o)
                .collect(groupingBy(o -> o, counting()))
                .values()
                .stream()
                .distinct()
                .count() == 1;

    }


}
