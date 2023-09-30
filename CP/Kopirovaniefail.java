import java.io.*;

public class Kopirovaniefail {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            try (FileReader reader = new FileReader("C:\\Users\\datch\\OneDrive\\Рабочий стол\\input.txt");
                 FileWriter writer = new FileWriter("C:\\Users\\datch\\OneDrive\\Рабочий стол\\output.txt")) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        Thread t2 = new Thread(() -> {
            try (FileReader reader = new FileReader("C:\\Users\\datch\\OneDrive\\Рабочий стол\\input2.txt");
                 FileWriter writer = new FileWriter("C:\\Users\\datch\\OneDrive\\Рабочий стол\\output2.txt")) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel execution time: " + (endTime - startTime) + "ms");
    }
}