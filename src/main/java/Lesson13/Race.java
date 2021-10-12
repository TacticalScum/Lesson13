package Lesson13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private final int carsCounter;
    private ArrayList<Stage> stages;
    private final CyclicBarrier startBarrier;
    private final CyclicBarrier finishBarrier;
    private static int winnersCounter;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int cars, Stage... stages) {
        this.carsCounter = cars;
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startBarrier = startBarrier();
        this.finishBarrier = finishBarrier();
    }

    public void startRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[carsCounter];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }

    private CyclicBarrier startBarrier() {
        return new CyclicBarrier(carsCounter, () ->
                System.out.println("\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!\n"));
    }

    private CyclicBarrier finishBarrier() {
        return new CyclicBarrier(carsCounter, () ->
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!"));
    }

    public synchronized void finish(Car car) {
        if (this.winnersCounter++ == 0) {
            System.out.printf("%s победил!\n\n", car.getName());
        } else {
            System.out.printf("%s пришел %s-м\n\n", car.getName(), this.winnersCounter);
        }
    }

    public CyclicBarrier getStartBarrier() {
        return startBarrier;
    }

    public CyclicBarrier getFinishBarrier() {
        return finishBarrier;
    }
}