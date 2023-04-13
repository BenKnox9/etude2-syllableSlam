import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** Class to test methods which compute the number of syllables in a word */
public class SyllableSlamApp {
    /** Hashmap to store words as keys and syllable counts as values */
    private static HashMap<String, Integer> syllableHashMap = new HashMap<>();

    /**
     * Method to scan words from stdin and prints the calculated syllable count
     */
    private static void scanWords() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            // scan new word from stdin + strip + lowercase
            String line = sc.nextLine();
            line = line.strip();
            line = line.toLowerCase();

            if (line.length() <= 0) { // print new line if no word
                System.out.println();
                continue;
            }

            int res;
            CountSyllables cs = new CountSyllables();
            // get num syllables from hashmap if present, otherwise from rule
            // based method
            if (syllableHashMap.containsKey(line)) {
                res = syllableHashMap.get(line);
            } else {
                res = cs.countSyllables(line);// calculated res
            }
            System.out.println(res);

        }

        sc.close();
    }

    /**
     * Method to help test program's accuracy when using two test files. The first
     * one being the input words (separated by a newline) and the second file being
     * the expected correct syllable count for each word.
     * The method takes in the first file of words and computes the syllable counts.
     * If the number of syllable counts is incorrect, the word is printed out with
     * the wrong syllable count compared to the correct syllable count.
     * Final statistics is printed out i.e. Incorrect words, total words and
     * percentage of words computed correctly
     * The files are expected to be in the current directory of this program.
     * 
     * @param fileName1 Filename of file consisting of words
     * @param fileName2 Filename of file with correct syllable counts to compare
     *                  against
     */
    private static void testingProgram(String fileName1, String fileName2) {
        try {
            Scanner scan1 = new Scanner(new File("./" + fileName1));
            Scanner scan2 = new Scanner(new File("./" + fileName2));
            int problemCount = 0;
            int countTotal = 0;
            while (scan1.hasNextLine()) {
                String line = scan1.nextLine(); // word from file
                String expResString = scan2.nextLine(); // expected count
                line = line.strip();
                line = line.toLowerCase();
                if (line.length() <= 0) {
                    System.out.println();
                    continue;
                }
                // choose whether to use hashset or rule-based
                int res;
                CountSyllables cs = new CountSyllables();
                if (syllableHashMap.containsKey(line)) {
                    res = syllableHashMap.get(line);
                } else {
                    res = cs.countSyllables(line);// calculated res
                }

                int expectedRes;
                try {
                    expectedRes = Integer.parseInt(expResString);
                } catch (NumberFormatException e) {
                    System.err.println("Is not a number");
                    continue;
                }
                // if incorrect syllable count
                if (res != expectedRes) {
                    problemCount++;
                    System.out.println(line + " " + res + " " + expectedRes);
                }
                countTotal++;
            }
            System.out.println("Tests Concluded");
            System.out.println("Incorrect words = " + problemCount);
            System.out.println("Total Words: " + countTotal);
            System.out.println("Percentage: " + (1.0 - (problemCount / (double) countTotal)));

        } catch (FileNotFoundException e) {
            System.err.println("File Not found\nUsage: java <program_name> <test_file_in> <test_file_out>");
        }
    }

    /** Main method for testing countSyllables method (and computing hashmap) */
    public static void main(String[] args) throws FileNotFoundException {
        final long startTime = System.currentTimeMillis();

        File testText = new File(
                "C:/Users/dud3h/Documents/COSC 326/COSC326-Syllable Slam/syllable_slam/oneSyllableTests.txt");

        String filename_keys = "dataset_keys.txt"; // input file of words for hashmap
        String filename_values = "dataset_values.txt"; // output file of expected syllable counts for hashmap
        Scanner sc_keys = new Scanner(new File(filename_keys));
        Scanner sc_vals = new Scanner(new File(filename_values));

        // create hash map from filename_keys and filename_values files
        // loop through each line in input file and get the word from each line to form
        // the hash map's keys.
        // At the same time, get the expected number of syllables counts for each word
        // from the second file to form hash map's values.
        while (sc_keys.hasNextLine()) {
            String l = sc_keys.nextLine();
            l = l.strip();
            l = l.toLowerCase();
            // s = scf1.nextLine().split("[ \\n]");
            Integer num = Integer.parseInt(sc_vals.nextLine());
            // for(String str : s ) arr.add(str);
            // arr_num.add(num);
            syllableHashMap.put(l, num);
        }

        if (args.length == 2) {// pass two filenames for using with test files (testing program)
            String fileName1 = args[0]; // in.txt (words)
            String fileName2 = args[1]; // out.txt (syllable counts)
            testingProgram(fileName1, fileName2);

        } else if (args.length == 0) { // run manually/normally via stdin
            scanWords();
            return;
        } else { // cmd line args must be either 0 or 2
            System.err.println("Usage: java <program_name>");
            return;
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }

}