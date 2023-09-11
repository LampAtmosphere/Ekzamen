import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cp7 {
public static void main(String[] args) {
try {
// Получаем объект Runtime
Runtime runtime = Runtime.getRuntime();

// Выполняем команду для получения списка процессов
Process process = runtime.exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

// Читаем вывод команды
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
String line;

// Пропускаем первые две строки (заголовки)
reader.readLine();
reader.readLine();

while ((line = reader.readLine()) != null) {
// Разбираем строку, чтобы получить PID и имя процесса
String[] parts = line.trim().split("\\s+");
String pid = parts[1];
String processName = parts[0];

// Выводим PID и имя процесса
System.out.println("PID: " + pid + ", Имя процесса: " + processName);
}

// Ждем завершения процесса
process.waitFor();

} catch (IOException | InterruptedException e) {
e.printStackTrace();
}
}
}