package Lesson13;

public class MainClass {
    public static void main(String[] args) {
        Race race = new Race(4,
                new Road(100),
                new Tunnel(40, 2),
                new Road(60));

        race.startRace();
    }
}