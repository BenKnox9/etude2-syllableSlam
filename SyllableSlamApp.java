import java.util.*;

public class SyllableSlamApp {

    // aexaemple
    //idle - 1
    // e is vowel, x not vowel
    //
    //do we want 'y' as a vowel too?
    private static String vowels = "aeiou";
    private static String letterY = "y";
    private static String letterE = "e";

    //need check for apostrophe words
    /** method to count and return num syllables in word
    * @param word word string to pass in
    * @return number of syllables*/
    private static int countSyllables(String word) {
        if (word.length() <= 2){
            return 1;
        }

        int clumpCount = 1;
        Boolean isVowel = false; // was the last letter a vowel
        
        if (containsVowel(word.charAt(0))) {
            isVowel = true;
        }

        for (int i = 1; i < word.length() -1;  i++) {
            //consonant+vowel
            if (!isVowel && containsVowel(word.charAt(i))) { // last read a consonant and now reading a vowel
                clumpCount++;
                isVowel = true;
            }//vowel+consonant
            else if (isVowel && !containsVowel(word.charAt(i))) { // last read a vowel and now reading a consonant
                clumpCount++;
                isVowel = false;
            }//consonant+y
            else if (!isVowel && letterY.contains(Character.toString(word.charAt(i)))){
                //check on right of y - if have vowel clump, don't increase vowel count
                if (checkForY(word, i)) {
                    clumpCount++;
                    isVowel= true;
                }
            }
        }
        //check for last letter e.g. y or le

        clumpCount += checkLastLetter(word);
    
        
        return clumpCount / 2;
    }

    
    /**
     * Checks if a character is a vowel
     * @param ch character to be checked
     * @return true if the character is a vowel, and false otherwise
     */
    private static boolean containsVowel(char ch) {
        boolean check = vowels.contains(Character.toString(ch));
        // System.out.println(check);
        return check;
    }

    /**
     * Checks if a given y is acting as a vowel, and thus should increment the syllable count
     * @param word word containing y
     * @param indOfY the index of the y character within that word
     * @return true if the y acts as a vowel, and false otherwise
     */
    private static boolean checkForY(String word, int indOfY) {
        //if there is a consonant to the right of y, or if its end of the word
        if((!containsVowel(word.charAt(indOfY + 1)))) {
            return true;
        }
        return false;
    }    
    
    private static int checkLastLetter(String word) {
        
        if(letterY.contains(Character.toString(word.charAt(word.length()-1)))) {
            //check previous letter
            // If word ends in y following vowel, don't add
            // else word ends in y following a consonant, add to the count.
            char x = word.charAt(word.length() - 2);
            if(containsVowel(x)) {
                return 0;
            }
            else {
                return 1;
            }
        }

        if(letterE.contains(Character.toString(word.charAt(word.length()-1)))) {
            //check previous letter
            // If word ends in an e following a consonant, don't add to count.
            // If word ends in an e following an l, add to the count.    
            char x = word.charAt(word.length() - 2);
            if (x == 'l'){
                return 2;
            }
            else if(!containsVowel(x)) {
                return 0;
            }
        }

        // TODO: check for flip between second to last and last letter
        
        return 0;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            line = line.strip();
            line.toLowerCase();
            System.out.println(countSyllables(line));   
        }
        sc.close();
    }
    

}