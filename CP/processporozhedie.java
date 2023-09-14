package CP;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cp6 {
    public static void main(String[] args) {
        int numberOfRuns = 10;
        long intervalMilliseconds = 50; // Интервал в миллисекундах
        
        
        for (int i = 0; i < numberOfRuns; i++) {
            try {
                Runtime.getRuntime().exec("notepad"); // Запуск процесса блокнота
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(intervalMilliseconds); // Ожидание до следующего запуска
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        
       
            }
        }
    }
