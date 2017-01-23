package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.DetailProcessQueue;
import pl.edu.agh.kis.solver.genetics.model.Genotype;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SchedulePopulation implements Population {

    public List<Schedule> getSchedules() {
        return schedules;
    }

    private final List<Schedule> schedules;

    public SchedulePopulation(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public SchedulePopulation(List<DetailProcessQueue> detailProcessQueues, Integer populationSize) {
        schedules = IntStream.rangeClosed(1, populationSize)
                .boxed()
                .map(integer -> new Schedule(detailProcessQueues))
                .collect(toList());
    }

    public SchedulePopulation(SchedulePopulation schedulePopulation) {
        List<Schedule> schedules;
        schedules = crossSchedules(schedulePopulation);
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        List<Schedule> mutated = schedules
                .stream()
                .map((schedule) -> {
                    int first = new Random().ints(0, schedule.getSchedule().size()).findFirst().getAsInt();
                    int second = new Random().ints(0, schedule.getSchedule().size()).findFirst().getAsInt();
                    return mutate(schedule, first, second);
                }).sorted(Comparator.comparing(fitnessCalculator::getFitness))
                .collect(toList());

        List<Schedule> result = new ArrayList<>();
        for (int i = 0; i < mutated.size() / 2; i++) {
            result.add(mutated.get(i));
        }
        for (int i = 0; i < mutated.size() / 2; i++) {
            result.add(mutated.get(i));
        }
        if (result.size() == mutated.size() - 1) {
            result.add(mutated.get(0));
        }
        this.schedules = result;
    }

    public List<Schedule> crossSchedules(SchedulePopulation schedulePopulation) {
        List<Schedule> schedules = new ArrayList<>();
        if (schedulePopulation.schedules.size() <= 1) {
            return schedulePopulation.schedules;
        }
        for (int i = 0; i < schedulePopulation.schedules.size() - 1; i++) {
            Schedule schedule = schedulePopulation.schedules.get(i);
            Schedule nextSchedule = schedulePopulation.schedules.get(i + 1);
//            int offset = new Random().ints(0, schedule.getSchedule().stream().mapToInt(value -> value.getProcesses().size()).findFirst().orElse(1) - 1).findFirst().orElse(1);
//            Schedule crossover = cycleCrossover(schedule, nextSchedule, offset);
            schedules.add(schedule);
        }
        schedules.add(schedulePopulation.schedules.get(schedules.size() - 1));
        return schedules;
    }

    public Schedule getWorst(FitnessCalculator fitnessCalculator) {
        int j = 0;

        return schedules.stream()
                .sorted(comparing(fitnessCalculator::getFitness).reversed())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void generatePopulation() {

    }

    public Schedule cycleCrossover(Schedule schedule1, Schedule schedule2, int offset) {
        int[] genotype = schedule1.getGenotype().genes;
        int[] genotype1 = schedule2.getGenotype().genes;
        Arrays.stream(genotype).limit(offset);

        int[] result = new int[genotype1.length];
        for (int i = 0; i < offset; i++) {
            result[i] = genotype[i];
        }
        for (int i = offset; i < genotype1.length; i++) {
            result[i] = genotype1[i];
        }

        return new Schedule(schedule1.getSchedule(), new Genotype(result));
    }

    @Override
    public Schedule mutate(Schedule schedule, int first, int second) {
        int[] genotype = schedule.getGenotype().genes;
        int i = genotype[first];
        genotype[first] = genotype[second];
        genotype[second] = i;

        return new Schedule(schedule.getSchedule(), new Genotype(genotype));
    }

    @Override
    public Schedule selectFittest(FitnessCalculator fitnessCalculator) {
        return schedules.stream()
                .sorted(comparing(fitnessCalculator::getFitness))
                .findFirst()
                .orElse(null);
    }

    @Deprecated
    public SchedulePopulation(int i) {
        this.schedules = null;
    }


}
