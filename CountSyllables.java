/** Class with methods to help count the number of syllables in a word */
public class CountSyllables {

    private static String vowels = "aeiou";
    private static String letterY = "y";
    private static String letterE = "e";

    /**
     * Method to count and return num syllables in word
     * 
     * @param word word string to pass in
     * @return number of syllables
     */

    public int countSyllables(String word) {
        if (word.length() <= 2) {
            return 1;
        }

        int clumpCount = 1;

        Boolean isVowel = false; // was the last letter a vowel
        // Boolean isI = false; // last letter was an I

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

        if (word.contains("ia") || word.contains("iu") || word.contains("io")) {
            clumpCount += 2;
        }

        clumpCount = clumpCount / 2;
        // decrease count if word ends in ion
        if (word.contains("ia") && word.substring(word.length() - 3).toLowerCase().equals("ion")) {
            clumpCount++;
        } else if (word.substring(word.length() - 3).toLowerCase().equals("ion")) {
            clumpCount--;
        }

        if (word.substring(word.length() - 3).toLowerCase().equals("ism")) {
            clumpCount++;
        }
        if (word.substring(word.length() - 3).toLowerCase().equals("ely")) {
            clumpCount--;
        }
        if (word.substring(word.length() - 3).toLowerCase().equals("que")
                || word.substring(word.length() - 3).toLowerCase().equals("gue")) {
            clumpCount--;
        }

        if (word.contains("ua") && word.indexOf("ua") > 0) {
            if (word.charAt(word.indexOf("ua") - 1) != 'q') {
                clumpCount++;
            }

        }
        // if (word.contains("ie") && word.indexOf("ie") > 0) {

        // clumpCount++;

        // }
        return clumpCount;
    }

    /**
     * Checks if a character is a vowel
     * 
     * @param ch character to be checked
     * @return true if the character is a vowel, and false otherwise
     */
    private boolean isVowel(char ch) {
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
    private boolean checkForY(String word, int indOfY) {
        // if there is a consonant to the right of y, or if its end of the word
        if ((!isVowel(word.charAt(indOfY + 1)))) {
            return true;
        }
        return false;
    }

    /**
     * Checks for special cases around last letter e.g. if it is C+y,
     * C+le or C+re, should add two extra syllable counts. If it is ending with ey
     * or C+V or V+C, it just adds one syllable counts, otherwise adds none.
     * 
     * @param word word with last letter to check
     * @return number of syllables to add on for last letter cases
     */
    private int checkLastLetter(String word) {
        // last letter is y
        if (letterY.contains(Character.toString(word.charAt(word.length() - 1)))) {
            // check 2nd last letter
            // If word ends in vowel+y, don't add
            // else word ends in consonant+y, add to the count.
            char x = word.charAt(word.length() - 2); // prev letter
            if (isVowel(x)) { // vowel+y
                if (x == 'e') { // ey
                    return 1;
                }
                return 0;
            } else { // consonant+y
                return 2;
            }
        }

        // last letter is e
        if (letterE.contains(Character.toString(word.charAt(word.length() - 1)))) {
            char x = word.charAt(word.length() - 2); // prev letter
            // Consonant + l + e
            if (x == 'l') {
                if (!isVowel(word.charAt(word.length() - 3))) {
                    return 2;
                } else {
                    return 0;
                }
            } else if (x == 'r') { // Consonant + r + e
                if (!isVowel(word.charAt(word.length() - 3))) {
                    return 2;
                } else {
                    return 0;
                }
            } else if (!isVowel(x)) { // consonant + e
                return 0;
            }
        }
        // consonant + vowel(last)
        if (!isVowel(word.charAt(word.length() - 2)) && isVowel(word.charAt(word.length() - 1))) {
            return 1;
        } // vowel+consonant(last)
        else if (isVowel(word.charAt(word.length() - 2)) && !isVowel(word.charAt(word.length() - 1))) {
            return 1;
        } // consonant+y
        return 0;
    }
}
