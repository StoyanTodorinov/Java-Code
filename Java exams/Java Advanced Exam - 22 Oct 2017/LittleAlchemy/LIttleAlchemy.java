import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class LIttleAlchemy {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> stones = new ArrayDeque<>();
        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < arr.length; i++) {
            stones.addLast(arr[i]);
        }

        int storageCount = 0;
        while (true) {
            String line = reader.readLine();
            if (line.equals("Revision")) {
                break;
            }
            String[] tokens = line.split("\\s+");
            if (tokens[0].equals("Apply")) {
                int timesToApply = Integer.parseInt(tokens[2]);
                for (int i = 0; i < timesToApply; i++) {
                    if (stones.isEmpty()) {
                        continue;
                    }
                    int currentStone = stones.removeFirst();
                    currentStone -= 1;
                    if (currentStone <= 0) {
                        storageCount++;
                    } else {
                        stones.addLast(currentStone);
                    }
                }
            } else if (tokens[0].equals("Air")) {
                int dose = Integer.parseInt(tokens[2]);
                if (storageCount >= 1) {
                    storageCount--;
                    stones.addLast(dose);
                }
            }
        }

        System.out.println(Arrays.toString(stones.toArray())
                .replace("[", "").replace("]", "").replace(",", ""));
        System.out.println(storageCount);
    }
}
