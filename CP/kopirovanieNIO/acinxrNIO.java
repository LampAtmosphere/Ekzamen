package kopirovanieNIO;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class acinxrNIO {    public static void main(String[] args) {
        Path sourceFile1 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop1.txt");
        Path sourceFile2 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop2.txt");
        Path targetFile1 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop1.txt");
        Path targetFile2 = Path.of("C:\\Users\\datch\\OneDrive\\Рабочий стол\\kop1.txt");

        long startTime = System.currentTimeMillis();

        try {
            AsynchronousFileChannel sourceChannel1 = AsynchronousFileChannel.open(sourceFile1, StandardOpenOption.READ);
            AsynchronousFileChannel sourceChannel2 = AsynchronousFileChannel.open(sourceFile2, StandardOpenOption.READ);
            AsynchronousFileChannel targetChannel1 = AsynchronousFileChannel.open(targetFile1, StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
            AsynchronousFileChannel targetChannel2 = AsynchronousFileChannel.open(targetFile2, StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);

            ByteBuffer buff1 = ByteBuffer.allocate(1024);
            ByteBuffer buff2 = ByteBuffer.allocate(1024);

            Future<Integer> future1 = sourceChannel1.read(buff1, 0);
            Future<Integer> future2 = sourceChannel2.read(buff2, 0);

            while (future1.isDone() == false || future2.isDone() == false) {
                // ждем завершения чтения
            }

            buff1.flip();
            buff2.flip();

            targetChannel1.write(buff1, 0);
            targetChannel2.write(buff2, 0);

            buff1.clear();
            buff2.clear();

            sourceChannel1.close();
            sourceChannel2.close();
            targetChannel1.close();
            targetChannel2.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Ассинхронное копирование выполнено.Время: " + duration + " мс");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
