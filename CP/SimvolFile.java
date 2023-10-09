import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SimvolFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\datch\\OneDrive\\Рабочий стол\\Symbol.txt");
        String content = null;
        try {
            content = Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maximum = 0;
        int CurrentP = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == 'X') {
                CurrentP++;
                if (CurrentP > maximum) {
                    maximum = CurrentP;
                }
            } else {
                CurrentP = 0;
            }
        }
        System.out.println("длинна наибольшей последовательности X = " + maximum);
    }
}