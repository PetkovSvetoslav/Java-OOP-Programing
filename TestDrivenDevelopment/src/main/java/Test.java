import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 3, 1, 2));
        nums.sort(Comparator.comparingInt(n -> n));

        for (Integer num : nums) {
            System.out.println(num);
        }

    }
}
