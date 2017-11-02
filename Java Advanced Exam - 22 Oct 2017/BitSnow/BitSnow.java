import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BitSnow {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split(", ");
        short[] numbers = new short[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = Short.parseShort(nums[i]);
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = numbers.length - 1; j >= 1; j--) {
                int temp = numbers[j];
                numbers[j] = (short) (numbers[j - 1] | numbers[j]);
                numbers[j - 1] = (short) (temp & numbers[j - 1]);
            }
        }

        System.out.println(Arrays.toString(numbers).replace("[", "").replace("]", ""));
    }
}
