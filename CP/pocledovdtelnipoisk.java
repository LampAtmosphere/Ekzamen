package CP;
import java.util.Random;
public class cp1 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] num = new int[1000];
        int max = 0;
        int max2 = 0;
        int max7 = 0;
        int max14 = 0;
        int otv1;
        int otv2;
        for(int i = 0;i < num.length;i++){
            num[i] = random.nextInt(10000);// массив рандомных знач.(предел 10000)
            if (num[i] > max) {
                max = num[i];
            }
            if ((num[i] % 2 == 0) && (num[i] > max2)) {
                max2 = num[i];
            }
            if ((num[i] % 7 == 0) && (num[i] > max7)) {//ищем кратные 2 ; 7 ; 14
                max7 = num[i];
            }
            if ((num[i] % 14 == 0) && (num[i] > max14)) {
                max14 = num[i];
            }
        }

        otv1 = max * max14;
        otv2 = max2 * max7;

        if (otv1 == 0 && otv2 == 0){
            System.out.println(-1);
        }
        else{
            System.out.println(Math.max(otv1, otv2));//ищем макс число
        }
    }
}
