import java.io.*;

public class LargeFileReadingComparison {
    public static void main(String[] args) {
        String filePath = "largefile.txt"; // Ensure this file exists and is ~500MB

        long start1 = System.nanoTime();
        try {
            FileReader fr = new FileReader(filePath);
            while (fr.read() != -1) {}
            fr.close();
        } catch (Exception e) {
            System.out.println("FileReader error: " + e.getMessage());
        }
        long end1 = System.nanoTime();
        long fileReaderTime = (end1 - start1) / 1_000_000;

        long start2 = System.nanoTime();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
            while (isr.read() != -1) {}
            isr.close();
        } catch (Exception e) {
            System.out.println("InputStreamReader error: " + e.getMessage());
        }
        long end2 = System.nanoTime();
        long inputStreamReaderTime = (end2 - start2) / 1_000_000;

        System.out.println("Reading 500MB file:");
        System.out.println("FileReader time: " + fileReaderTime + " ms");
        System.out.println("InputStreamReader time: " + inputStreamReaderTime + " ms");
    }
}

