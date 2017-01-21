package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

    public SchedulePopulation(List<Process> processes, Integer populationSize) {
        schedules = IntStream.rangeClosed(1, populationSize)
                .boxed()
                .map(integer -> new Schedule(processes))
                .collect(toList());
    }

    public SchedulePopulation(SchedulePopulation schedulePopulation) {
        List<Schedule> schedules;
        schedules = crossSchedules(schedulePopulation);
        this.schedules = schedules
                .stream()
                .map((schedule) -> {
                    int first = new Random().ints(0, schedule.getSchedule().size()).findFirst().getAsInt();
                    int second = new Random().ints(0, schedule.getSchedule().size()).findFirst().getAsInt();
                    return mutate(schedule, first, second);
                })
                .collect(toList());
    }

    public List<Schedule> crossSchedules(SchedulePopulation schedulePopulation) {
        List<Schedule> schedules = new ArrayList<>();
        if (schedulePopulation.schedules.size() <= 1) {
            return schedulePopulation.schedules;
        }
        for (int i = 0; i < schedulePopulation.schedules.size() - 1; i++) {
            Schedule schedule = schedulePopulation.schedules.get(i);
            Schedule nextSchedule = schedulePopulation.schedules.get(i + 1);
            List<Process> crossover = cycleCrossover(schedule, nextSchedule, new Random().ints(0, schedule.getSchedule().size() - 1).findFirst().orElse(1));
            Schedule crossed = new Schedule(crossover);
            schedules.add(crossed);
        }
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

    public List<Process> cycleCrossover(Schedule schedule1, Schedule schedule2, int offset) {
        List<Process> processes1 = schedule1.getSchedule().subList(0, offset);
        List<Process> processes2 = schedule2.getSchedule().stream().filter(process -> !processes1.contains(process)).collect(toList());
        List<Process> result = new ArrayList<>(processes1);
        result.addAll(processes2);
        return result.stream().collect(toList());
    }

    @Override
    public Schedule mutate(Schedule schedule, int first, int second) {
        int temp1 = first, temp2 = second;
        if (first > second) {
            temp1 = second;
            temp2 = first;
        }
        if (first == second) {
            temp1 = schedule.getSchedule().size() / 2;
            temp2 = temp1 + 1;
        }
        List<Process> collect1 = schedule.getSchedule().stream().collect(toList());
        Collections.swap(collect1, temp1, temp2);

//        Process process = schedule.getSchedule().get(temp1);
//        Process process1 = schedule.getSchedule().get(temp2);
//        List<Process> processes1 = schedule.getSchedule().subList(0, temp1);
//        List<Process> processes2 = schedule.getSchedule().subList(temp1 + 1, temp2);
//        processes1.add(process1);
//        List<Process> processes3 = schedule.getSchedule().subList(temp2 + 1, schedule.getSchedule().size());
//        processes2.add(process);
//        processes1.addAll(processes2);
//        List<Process> collect = Stream.of(processes1, singletonList(process1), processes2, singletonList(process), processes3).flatMap(Collection::stream).collect(toList());
        return new Schedule(schedule.getDetails(), schedule.getMachines(), collect1);
//        List<Process> processes = schedule
//                .getSchedule()
//                .stream()
//                .map(process -> new Process(process.getMachine(), process.getDetail(), process.getOperationTime(), mutateProcessStartTime(process)))
//                .collect(toList());
//        return new Schedule(schedule.getDetails(), schedule.getMachines(), processes);
    }

    private int mutateProcessStartTime(Process process) {
        int result = process.getStartTime() + (int) (Math.random() * 4) - 2;
        return result > 0 ? result : process.getStartTime();
    }

    @Override
    public List<Process> crossover(Schedule schedule1, Schedule schedule2) {
        List<Process> processes1 = schedule1.getSchedule();
        List<Process> processes2 = schedule2.getSchedule();
        return IntStream.range(0, processes1.size())
                .boxed()
                .map(integer -> crossoverProcess(processes1, processes2, integer))
                .collect(toList());
    }

    private Process crossoverProcess(List<Process> processes1, List<Process> processes2, Integer integer) {

        Process process = processes1.get(integer);
        Process process1 = processes2.get(integer);
        int startTime = integer % 2 == 0 ? process.getStartTime() : process1.getStartTime();
        return new Process(process.getMachine(), process.getDetail(), process.getOperationTime(), startTime);
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
