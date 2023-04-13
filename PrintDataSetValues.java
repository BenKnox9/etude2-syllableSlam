import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class to print syllable counts from dataset_keys.txt by using
 * dataset_space_separated.txt to count the number of tokens on each line.
 * It prints it to stdout, then use java PrintDataSetValues.java > filename.txt
 * to store the counts in that file. (In this case, our filename used was
 * dataset_values.txt)
 * 
 * @author Ruth Huang
 */
public class PrintDataSetValues {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("dataset_space_separated.txt"));

            while (sc.hasNextLine()) {
                StringTokenizer str_arr = new StringTokenizer(sc.nextLine());
                System.out.println(str_arr.countTokens());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
