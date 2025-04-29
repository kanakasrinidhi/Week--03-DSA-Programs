import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WordCountInFile {
    public static void main(String[] args) {
        String filePath = "sample.txt"; // Make sure this file exists
        String targetWord = "java";     // Change this to the word you want to count
        int count = 0;

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }

            br.close();
            fr.close();

            System.out.println("The word '" + targetWord + "' occurred " + count + " times in the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
