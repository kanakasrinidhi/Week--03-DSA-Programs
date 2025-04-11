import java.util.Arrays;
import java.util.Scanner;

public class FirstMissingPositiveAndBinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the list
        System.out.print("Enter the number of elements in the list: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter the elements of the list:");

        // Input the elements into the array
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Finding the first missing positive integer using Linear Search
        int firstMissingPositive = findFirstMissingPositive(arr);
        System.out.println("First missing positive integer: " + firstMissingPositive);

        // Input the target number to find its index using Binary Search
        System.out.print("Enter the target number to search for: ");
        int target = scanner.nextInt();

        // Sorting the array before Binary Search
        Arrays.sort(arr);

        // Finding the index of the target using Binary Search
        int targetIndex = binarySearch(arr, target);
        if (targetIndex != -1) {
            System.out.println("Target found at index: " + targetIndex);
        } else {
            System.out.println("Target not found.");
        }

        scanner.close();
    }

    // Function to find the first missing positive integer using Linear Search
    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;

        // Step 1: Mark numbers as negative at their corresponding index positions
        for (int i = 0; i < n; i++) {
            int num = Math.abs(arr[i]);
            if (num <= n && arr[num - 1] > 0) {
                arr[num - 1] = -arr[num - 1]; // Mark the number as visited
            }
        }

        // Step 2: Find the first index that is not marked, which corresponds to the first missing positive integer
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                return i + 1; // The first missing positive integer
            }
        }

        return n + 1; // If no missing positive integer in the range [1, n], return n + 1
    }

    // Function to perform Binary Search to find the index of a target in a sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Target found at index mid
            } else if (arr[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return -1; // Target not found
    }
}
