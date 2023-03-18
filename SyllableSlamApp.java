import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** Class to test methods which compute the number of syllables in a word */
public class SyllableSlamApp {
    /** Hashmap to store words as keys and syllable counts as values */
    private static HashMap<String, Integer> syllableSplits = new HashMap<>();

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
            if (syllableSplits.containsKey(line)) {
                res = syllableSplits.get(line);
            } else {
                res = cs.countSyllables(line);// calculated res
            }
            System.out.println(res);

        }

        sc.close();
    }

    /**
     * Method to help with testing program by passing two files. The first one being
     * words
     * separated on each line, and the expected syllable count for each word(s).
     * If the number of syllable counts are incorrect, the word is printed out with
     * the wrong syllable count compared to the correct count.
     * 
     * @param fileName1 Name of file with words
     * @param fileName2 Name of file with correct syllable counts to compare against
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
                // choose whether to use hashset or
                int res;
                CountSyllables cs = new CountSyllables();
                if (syllableSplits.containsKey(line)) {
                    res = syllableSplits.get(line);
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

                if (res != expectedRes) {
                    problemCount++;
                    System.out.println(line + " " + res + " " + expectedRes);
                }
                countTotal++;
            }
            System.out.println("Tests Concluded");
            System.out.println("Problem count = " + problemCount);
            System.out.println("Total: " + countTotal);
            System.out.println("Percentage: " + (1.0 - (problemCount / (double) countTotal)));

        } catch (FileNotFoundException e) {
            System.err.println("File Not found\nUsage: java <program_name> <test_file>");
        }
    }

    /** Main method for testing countSyllables method (and computing hashmap) */
    public static void main(String[] args) throws FileNotFoundException {

        final long startTime = System.currentTimeMillis();

        File testText = new File(
                "C:/Users/dud3h/Documents/COSC 326/COSC326-Syllable Slam/syllable_slam/oneSyllableTests.txt");

        String filename_in = "mhyph.txt"; // input file of words for hashmap
        String filename_out = "mhyph_out.txt"; // output file of expected syllable counts for hashmap
        Scanner scf1 = new Scanner(new File(filename_in));
        Scanner scf2 = new Scanner(new File(filename_out));

        // create hash map from dict
        // loop through each line in input file of words for hashmap, add it
        // to the key for the hashmap
        // and add the expected number of syllables per word from second file
        // to hashmap values
        while (scf1.hasNextLine()) {
            String l = scf1.nextLine();
            l = l.strip();
            l = l.toLowerCase();
            // s = scf1.nextLine().split("[ \\n]");
            Integer num = Integer.parseInt(scf2.nextLine());
            // for(String str : s ) arr.add(str);
            // arr_num.add(num);
            syllableSplits.put(l, num);
        }

        if (args.length == 2) {// for passing in test files (testing program)
            String fileName1 = args[0]; // words/in.txt
            String fileName2 = args[1]; // out.txt
            testingProgram(fileName1, fileName2);

        } else if (args.length != 0) { // needs be zero args for normal testing
            System.err.println("Usage: java <program_name>");
            return;
        } else if (args.length == 0) { // normal testing via stdin
            scanWords();

        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));

    }

}