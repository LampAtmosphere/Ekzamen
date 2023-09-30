import java.util.Scanner;

public class Chocolatka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите кол-во имеющихся денег: ");
        int money = scanner.nextInt();

        System.out.println("Введите цену 1 товара: ");
        int price = scanner.nextInt();

        System.out.println("Введите кол-во обёрток: ");

        int wrap = scanner.nextInt();
        int chocolates = money / price;
        int wrappers = chocolates;
        
        while (wrappers >= wrap) {
            int extraChocolates = wrappers / wrap;
            chocolates += extraChocolates;
            wrappers = wrappers % wrap + extraChocolates;
        }
        System.out.println(chocolates);
    }
}