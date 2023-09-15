class AnimalThread extends Thread {
    private String name;
    private int priority;
    private int distance = 0;

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public void run() {
        while (distance < 100) {
            distance += 10;
            System.out.println(name + " преодолел " + distance + " метров.");

            try {
                Thread.sleep(100); // Приостанавливаем поток на 100 мс.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " финишировал!");
    }
}

public class cp8 {
    public static void main(String[] args) {
        AnimalThread krol = new AnimalThread("Кролик", Thread.MAX_PRIORITY);
        AnimalThread turtik = new AnimalThread("Черепаха", Thread.MIN_PRIORITY);

        krol.start();
        turtik.start();
    }
}