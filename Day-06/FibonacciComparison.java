public class FibonacciComparison {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 30;

        long start1 = System.nanoTime();
        int fibRec = fibonacciRecursive(n);
        long end1 = System.nanoTime();
        long timeRecursive = (end1 - start1) / 1_000_000;

        long start2 = System.nanoTime();
        int fibIter = fibonacciIterative(n);
        long end2 = System.nanoTime();
        long timeIterative = (end2 - start2) / 1_000_000;

        System.out.println("Fibonacci of " + n + ":");
        System.out.println("Recursive result: " + fibRec + ", Time: " + timeRecursive + " ms");
        System.out.println("Iterative result: " + fibIter + ", Time: " + timeIterative + " ms");
    }
}
