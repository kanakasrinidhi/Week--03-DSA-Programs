import java.util.*;

public class SortingEle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the array elements separated by space:");
        String[] input = sc.nextLine().split(" ");
        int n = input.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int[] pattern = {2, 1, 8, 3};
        List<Integer> result = new ArrayList<>();

        int[] freq = new int[100];
        for (int num : arr) {
            freq[num]++;
        }

        for (int num : pattern) {
            while (freq[num] > 0) {
                result.add(num);
                freq[num]--;
            }
        }

        for (int i = 0; i < freq.length; i++) {
            while (freq[i] > 0) {
                result.add(i);
                freq[i]--;
            }
        }

        System.out.println("Reordered array: " + result);
    }
}