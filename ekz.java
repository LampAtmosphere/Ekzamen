import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ekz {
    public static void main(String[] args) {
        String name1, name2;
        int age1, age2;

        try {
            System.out.print("Введите имя первого человека: ");
            name1 = System.console().readLine();
            System.out.print("Введите возраст первого человека: ");
            age1 = Integer.parseInt(System.console().readLine());

            System.out.print("Введите имя второго человека: ");
            name2 = System.console().readLine();
            System.out.print("Введите возраст второго человека: ");
            age2 = Integer.parseInt(System.console().readLine());

            Person person1 = new Person(name1, age1);
            Person person2 = new Person(name2, age2);

            try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\datch\\OneDrive\\Рабочий стол\\otvet.txt");
                 ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

                objOut.writeObject(person1);
                objOut.writeObject(person2);

                System.out.println("объект сериализовался)");

            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное число.");
        }
    }
}
