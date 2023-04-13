/**
 * Class with methods to help count the number of syllables in a word
 * 
 * @author Rhys O'Higgins
 * @author Daniel Bohnic
 * @author Ben Knox
 * @author Ruth Huang
 */
public class CountSyllables {

    private static String vowels = "aeiou";
    private static String letterY = "y";
    private static String letterE = "e";

    /**
     * Method to count and return number of syllables in a word. A clump count is
     * kept which increases each time a consonant changes to a vowel or vice versa.
     * If there is a 'y' in the middle of the word acting as a vowel, it is also
     * increased. If the last letter increases the clump count, we also add it on.
     * We then divide the clump count by two, which calculates the approximate
     * syllable count.
     * However, there are three letter exceptions e.g. ion, ism etc, which would add
     * extra syllable sounds or remove them, which we individually cater for by
     * adding one or subtracting one from the syllable count at the end.
     * 
     * @param word word to compute number of syllables of
     * @return number of syllables in word
     */

    public int countSyllables(String word) {
        // One syllable for words shorter or equal to 2 letters
        // Ensures words must be of length 3 or greater
        if (word.length() <= 2) {
            return 1;
        }

        int clumpCount = 1;
        Boolean isVowel = false; // was the last letter a vowel
        // Boolean isI = false; // last letter was an I

        // Set isVowel true if first letter is vowel
        // else it is set to false
        if (isVowel(word.charAt(0))) {
            isVowel = true;
        }
        // loop from 2nd to second last letter
        for (int i = 1; i < word.length() - 1; i++) {
            // Notation: prev+current

            // consonant+vowel
            if (!isVowel && isVowel(word.charAt(i))) { // last read a consonant and now reading a vowel
                clumpCount++;
                isVowel = true;
            } // vowel+consonant
            else if (isVowel && !isVowel(word.charAt(i))) { // last read a vowel and now reading a consonant
                clumpCount++;
                isVowel = false;
            } // consonant+y
            else if (!isVowel && letterY.equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                // check on right of y - don't increase clumpCount if there's a vowel clump on
                // right of y, otherwise increase the clumpCount
                if (checkForY(word, i)) {
                    clumpCount++;
                    isVowel = true;
                }
            }
        }
        // check for last letter e.g. y or le
        // Add extra clump counts for last letter (2 if add a syllable else 0)
        clumpCount += checkLastLetter(word);

        // increase by 2 if contains ia, iu or io
        if (word.contains("ia") || word.contains("iu") || word.contains("io")) {
            clumpCount += 2;
        }
        // halves the clump count
        clumpCount = clumpCount / 2;
        // store last three letters
        String last_threeL = word.substring(word.length() - 3).toLowerCase();
        // increase if contains ia and ends in ion
        if (word.contains("ia") && last_threeL.equals("ion")) {
            clumpCount++;
        } // decrease if word ends in ion
        else if (last_threeL.equals("ion")) {
            clumpCount--;
        }
        // increase if last three letters is "ism"
        if (last_threeL.equals("ism")) {
            clumpCount++;
        }
        // decrease if last three letters is "ely"
        if (last_threeL.equals("ely")) {
            clumpCount--;
        }
        // decrease if last three letters is "que" or "gue"
        if (last_threeL.equals("que")
                || last_threeL.equals("gue")) {
            clumpCount--;
        }
        // increase if word contains ua, but doesn't begin with "ua" and the letter
        // before "ua" is not 'q'
        int ua_ind = word.indexOf("ua");
        if (ua_ind > 0 && word.charAt(ua_ind - 1) != 'q') {
            clumpCount++;

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
        return check;
    }

    /**
     * Checks if a given y is acting as a vowel, returning true if it is and false
     * if not. A 'y' acts as a vowel if there is a consonant after it or is the end
     * of the word. It does not act as a vowel if there is a vowel on the right of
     * it.
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
        // return false if is a vowel on right of y
        return false;
    }

    /**
     * Checks for special cases around last letter e.g. if it is C+y,
     * C+le or C+re, should add two extra clumpCounts i.e. one syllable count. If it
     * is ending with ey or C+V or V+C, it just adds one clumpCount, otherwise
     * adds none.
     * 
     * @param word word with last letter to check
     * @return number of syllables to add on for last letter cases
     */
    private int checkLastLetter(String word) {
        String lastLetter = String.valueOf(word.charAt(word.length() - 1));
        char secondLastChar = word.charAt(word.length() - 2);
        // last letter is y
        if (lastLetter.equalsIgnoreCase(letterY)) {
            // check 2nd last letter
            // If word ends in vowel+y, don't add
            // else word ends in consonant+y, add to the count.

            if (isVowel(secondLastChar)) { // vowel+y
                // if ey, add half, else other vowel+y, return 0
                if (secondLastChar == 'e') { // ey, add half
                    return 1;
                }
                return 0;
            } else { // consonant+y, add 1 to count
                return 2;
            }
        }

        // last letter is e
        if (lastLetter.equalsIgnoreCase(letterE)) {
            char thirdLastChar = word.charAt(word.length() - 3);
            // Consonant + l + e : add to count
            if (secondLastChar == 'l') {
                if (!isVowel(thirdLastChar)) {
                    return 2;
                }
            } // Consonant + r + e : add
            else if (secondLastChar == 'r') {
                if (!isVowel(thirdLastChar)) {
                    return 2;
                }
            } // Consonant + e : don't add
            else if (!isVowel(secondLastChar)) {
                return 0;
            }
            return 0;
        }
        // consonant + vowel(last)
        if (!isVowel(secondLastChar) && isVowel(lastLetter.charAt(0))) {
            return 1;
        } // vowel+consonant(last)
        else if (isVowel(secondLastChar) && !isVowel(lastLetter.charAt(0))) {
            return 1;
        } // consonant+y
        return 0;
    }
}
