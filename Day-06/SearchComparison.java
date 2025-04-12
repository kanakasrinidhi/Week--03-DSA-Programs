import java.util.Random;

public class SearchComparison {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        int target = -1; // target we know is not in the array to simulate worst-case

        for (int size : sizes) {
            int[] data = generateData(size);

            // Linear Search
            long startLinear = System.nanoTime();
            linearSearch(data, target);
            long endLinear = System.nanoTime();
            long timeLinear = endLinear - startLinear;

            // Binary Search - needs sorted data
            sortArray(data); // Simple bubble sort to avoid inbuilt sort

            long startBinary = System.nanoTime();
            binarySearch(data, target);
            long endBinary = System.nanoTime();
            long timeBinary = endBinary - startBinary;

            System.out.println("Dataset size: " + size);
            System.out.println("Linear Search time: " + timeLinear / 1_000_000.0 + " ms");
            System.out.println("Binary Search time: " + timeBinary / 1_000_000.0 + " ms");
            System.out.println("-----------------------------------------");
        }
    }

    static int[] generateData(int size) {
        int[] data = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(size * 2); // fill with random values
        }
        return data;
    }

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    static void sortArray(int[] arr) {
        // Simple bubble sort (slow for big sizes but no inbuilt functions)
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
