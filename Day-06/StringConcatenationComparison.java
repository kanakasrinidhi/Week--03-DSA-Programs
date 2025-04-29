public class StringConcatenationComparison {
    public static void main(String[] args) {
        int N = 1_000_000;

        long start1 = System.nanoTime();
        String str = "";
        for (int i = 0; i < N; i++) {
            str = str + "a";
        }
        long end1 = System.nanoTime();
        long timeString = (end1 - start1) / 1_000_000;

        long start2 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("a");
        }
        long end2 = System.nanoTime();
        long timeBuilder = (end2 - start2) / 1_000_000;

        long start3 = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < N; i++) {
            sbuf.append("a");
        }
        long end3 = System.nanoTime();
        long timeBuffer = (end3 - start3) / 1_000_000;

        System.out.println("Concatenating " + N + " strings:");
        System.out.println("String time: " + timeString + " ms");
        System.out.println("StringBuilder time: " + timeBuilder + " ms");
        System.out.println("StringBuffer time: " + timeBuffer + " ms");
    }
}
