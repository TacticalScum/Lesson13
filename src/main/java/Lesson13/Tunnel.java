package Lesson13;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore semaphore;
    private final int carsAtAll;

    public Tunnel(int length, int carsAtAll) {
        this.length = length;
        this.carsAtAll = carsAtAll;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(this.carsAtAll);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                semaphore.acquire();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}