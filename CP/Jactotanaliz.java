package CP;
import java.util.*;

public class cp4 {
    public static void main(String[] args) {
        Random random = new Random();
        int Pr = random.nextInt(10000);

        Map<Integer, Integer> votes = new HashMap<>();

        for (int i = 0; i < Pr; i++) {
            int pairNumber = random.nextInt(16) + 1;
            votes.put(pairNumber, votes.getOrDefault(pairNumber, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> sortedVotes = new ArrayList<>(votes.entrySet());
        sortedVotes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : sortedVotes) {
            if (count >= 3) {
                break;
            }
            int teamNumber = entry.getKey();
            int voteCount = entry.getValue();
            System.out.println("Пара под номером " + teamNumber + " набрала " + voteCount + " голосов!");
            count++;
        }
    }
}
