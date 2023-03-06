import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SyllableSlamApp {

    // aexaemple
    // idle - 1
    // e is vowel, x not vowel
    //
    // do we want 'y' as a vowel too?
    private static String vowels = "aeiou";
    private static String letterY = "y";
    private static String letterE = "e";

    // need check for apostrophe words
    /**
     * method to count and return num syllables in word
     * 
     * @param word word string to pass in
     * @return number of syllables
     */
    private static int countSyllables(String word) {
        if (word.length() <= 2) {
            return 1;
        }

        int clumpCount = 1;
        Boolean isVowel = false; // was the last letter a vowel

        if (isVowel(word.charAt(0))) {
            isVowel = true;
        }
        // loop from 2nd to second last letter
        for (int i = 1; i < word.length() - 1; i++) {
            // consonant+vowel
            if (!isVowel && isVowel(word.charAt(i))) { // last read a consonant and now reading a vowel
                clumpCount++;
                isVowel = true;
            } // vowel+consonant
            else if (isVowel && !isVowel(word.charAt(i))) { // last read a vowel and now reading a consonant
                clumpCount++;
                isVowel = false;
            } // consonant+y
            else if (!isVowel && letterY.contains(Character.toString(word.charAt(i)))) {
                // check on right of y - if have vowel clump, don't increase vowel count
                if (checkForY(word, i)) {
                    clumpCount++;
                    isVowel = true;
                }
            }
        }
        // check for last letter e.g. y or le

        clumpCount += checkLastLetter(word);

        return clumpCount / 2;
    }

    /**
     * Checks if a character is a vowel
     * 
     * @param ch character to be checked
     * @return true if the character is a vowel, and false otherwise
     */
    private static boolean isVowel(char ch) {
        boolean check = vowels.contains(Character.toString(ch));
        // System.out.println(check);
        return check;
    }

    /**
     * Checks if a given y is acting as a vowel, and thus should increment the
     * syllable count
     * 
     * @param word   word containing y
     * @param indOfY the index of the y character within that word
     * @return true if the y acts as a vowel, and false otherwise
     */
    private static boolean checkForY(String word, int indOfY) {
        // if there is a consonant to the right of y, or if its end of the word
        if ((!isVowel(word.charAt(indOfY + 1)))) {
            return true;
        }
        return false;
    }

    private static int checkLastLetter(String word) {
        // last letter is y
        if (letterY.contains(Character.toString(word.charAt(word.length() - 1)))) {
            // check 2nd last letter
            // If word ends in vowel+y, don't add
            // else word ends in consonant+y, add to the count.
            char x = word.charAt(word.length() - 2);
            if (isVowel(x)) {
                return 0;
            } else {
                return 1;
            }
        }
        // last letter is e
        if (letterE.contains(Character.toString(word.charAt(word.length() - 1)))) {
            // check previous letter
            // If word ends in l+e return 2
            // If word ends in consonant+e, return 0
            char x = word.charAt(word.length() - 2);
            if (x == 'l') {
                return 2;
            } else if (!isVowel(x)) {
                return 0;
            }
        }

        if (!isVowel(word.charAt(word.length()-2)) && isVowel(word.charAt(word.length()-1))) { // last read a consonant and now reading a vowel
            return 1;
        } // vowel+consonant
        else if (isVowel(word.charAt(word.length()-2)) && !isVowel(word.charAt(word.length()-1))) { // last read a vowel and now reading a consonant
            return 1;
        } // consonant+y
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File testText = new File("C:/Users/dud3h/Documents/COSC 326/COSC326 - Syllable Slam/syllable_slam/threeSyllableTest.txt");
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(testText);

        if (args.length != 1) {
            System.err.println("Usage: java cmd <numsyllables>");
        }
        int numSyllables = Integer.parseInt(args[0]);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.strip();
            line = line.toLowerCase();
            int res = countSyllables(line);
            if (res != numSyllables) {

                System.out.println(line + " " + countSyllables(line));
            }
        }
        System.out.println("Tests Concluded");
        sc.close();
    }

}