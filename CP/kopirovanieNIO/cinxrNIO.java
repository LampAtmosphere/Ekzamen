package kopirovanieNIO;
import java.io.IOException;
import java.nio.file.*;

public class cinxrNIO {
    public static void main(String[] args) {
        Path sourceFile = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop1.txt");
        Path destinationFile = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop2.txt");

        Path sourceFile2 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop1.txt");
        Path destinationFile2 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop2.txt");

        long startTime = System.nanoTime();
        try {
            Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(sourceFile2, destinationFile2, StandardCopyOption.REPLACE_EXISTING);
            long endTime = System.nanoTime();

            double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
            System.out.println("Синхронное копирование файлов произошло за " + elapsedTimeMs + " мс");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}