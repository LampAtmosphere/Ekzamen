package CP;
import java.util.Scanner;

public class cp5 {
    public static int multiply(int a, int b) {
        boolean isNegative = (a < 0 && b > 0) || (a > 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        int result = 0;

        while (b > 0) {
            if ((b & 1) == 1) {
                result += a;
            }
            a <<= 1;
            b >>= 1;
        }

        return isNegative ? -result : result;
    }

    public static int multiplyAlt(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        }

        if (a < 0) {
            a = -a;
            b = -b;
        }

        int result = 0;

        for (int i = 0; i < b; i++) {
            result += a;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        int result = multiply(a, b);
        System.out.println("Результат (Метод 1): " + result);

        int altResult = multiplyAlt(a, b);
        System.out.println("Результат (Метод 2): " + altResult);
    }
}
