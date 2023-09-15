public class ThreadExample extends Thread {
    public void run() {
        // Код, который будет выполняться в потоке
        System.out.println("Поток запущен");
    }

    public static void main(String[] args) {
        // Создание и запуск 10 потоков
        for (int i = 1; i <= 10; i++) {
            ThreadExample thread = new ThreadExample();
            thread.start();
        }
    }
}