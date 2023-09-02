import java.util.Random;

public class cp1 {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 1000;
        int[] mac1 = new int[size];

        for (int i = 0; i < size; i++) {
            mac1[i] = random.nextInt(10001);
        }

        int max_R = -1;

        for (int i = 0; i < mac1.length; i++) {
            for (int j = i + 1; j < mac1.length; j++) {
                int product = mac1[i] * mac1[j];
                if (product % 14 == 0 && product > max_R && product <= 10000) {
                    max_R = product;
                }
            }
        }

        System.out.println("Максимальное число R: " + max_R);
    }
}
