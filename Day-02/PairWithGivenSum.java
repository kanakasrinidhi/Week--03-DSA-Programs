import java.util.*;

public class PairWithGivenSum {
    public static void main(String[] args) {
        int[] arr = {8, 7, 2, 5, 3, 1};
        int target = 10;

        if (hasPairWithSum(arr, target)) {
            System.out.println("Pair with sum " + target + " exists.");
        } else {
            System.out.println("No pair with sum " + target + " found.");
        }
    }

    static boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }
}
