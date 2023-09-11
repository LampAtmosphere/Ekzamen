import java.util.Scanner;

public class laba5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        double[] array = new double[n];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextDouble();
        }

        scanner.close();

        int minIndex = findMinIndex(array);
        int maxIndex = findMaxIndex(array);

        double minValue = array[minIndex];
        double maxValue = array[maxIndex];

        System.out.println("Минимальное значение: " + minValue);
        System.out.println("Максимальное значение: " + maxValue);
    }

    public static int findMinIndex(double[] array) {
        int minIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static int findMaxIndex(double[] array) {
        int maxIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}