package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.genetics.model.Process;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SchedulePopulation implements Population {

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
                .map(this::mutate)
                .collect(toList());
    }

    private List<Schedule> crossSchedules(SchedulePopulation schedulePopulation) {
        List<Schedule> schedules = new ArrayList<>();
        if (schedulePopulation.schedules.size() <= 1) {
            return schedulePopulation.schedules;
        }
        for (int i = 0; i < schedulePopulation.schedules.size() - 1; i++) {
            Schedule schedule = schedulePopulation.schedules.get(i);
            Schedule nextSchedule = schedulePopulation.schedules.get(i + 1);
            List<Process> crossover = crossover(schedule, nextSchedule);
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

    @Override
    public Schedule mutate(Schedule schedule) {
        List<Process> processes = schedule
                .getSchedule()
                .stream()
                .map(process -> new Process(process.getMachine(), process.getDetail(), process.getOperationTime(), mutateProcessStartTime(process)))
                .collect(toList());
        return new Schedule(schedule.getDetails(), schedule.getMachines(), processes);
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
