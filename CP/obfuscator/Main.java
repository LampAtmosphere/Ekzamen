import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        // Путь к файлу, указанный пользователем
        String filePath = "C:\\Users\\datch\\OneDrive\\Документы\\GitHub\\Ekzamen\\CP\\obfuscator\\textonjava.txt";

        try {
            String line = "";
            // Открываем входной файл
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Создаем выходной файл
            BufferedWriter writer = new BufferedWriter(new FileWriter("обработанный_файл.txt"));

            // 3. Замена имени файла класса и конструкторов
           

            while ((line = reader.readLine()) != null) {
                // 1. Удаление лишних пробелов и символов перехода на новую строку
                line = line.trim();

                // 2. Удаление комментариев
                line = line.replaceAll("//.*|/\\*(.|\\n)*?\\*/", "");

                // 4. Замена идентификаторов на односимвольные или двухсимвольные имена
                //line = replaceIdentifiers(line);
                 line = line.replaceAll("public\\s+class\\s+.*\\s*\\{", "public class File {");
                System.out.println("Имя класса успешно изменено.");
                //line = line.replaceAll("public\\s+.*\\s+\\{", "constructor");

                // Записываем обработанную строку в выходной файл
                writer.write(line);
                writer.newLine();
            }

            // Закрываем файлы
            reader.close();
            writer.close();

            System.out.println("Операции завершены успешно. Результат сохранен в 'обработанный_файл.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Функция для замены идентификаторов на односимвольные или двухсимвольные имена ПокаЧТо не работает!!!
    private static String replaceIdentifiers(String line) {
        // Создаём таблицу замены для односимвольных имен
        Map<String, Character> identifierMap = new HashMap<>();
        char nextChar = 'a'; // Начнем с буквы 'a'

        // Найдем все идентификаторы в строке с помощью регулярных выражений
        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String identifier = matcher.group();
            // Проверим, существует ли уже замена для данного идентификатора
            if (!identifierMap.containsKey(identifier)) {
                // Если нет, добавим новую замену
                identifierMap.put(identifier, nextChar++);
            }
        }

        // Выполним замену в строке
        for (Map.Entry<String, Character> entry : identifierMap.entrySet()) {
            String oldIdentifier = entry.getKey();
            char newIdentifier = entry.getValue();
            line = line.replaceAll("\\b" + oldIdentifier + "\\b", Character.toString(newIdentifier));
        }
        return line;
    }
}