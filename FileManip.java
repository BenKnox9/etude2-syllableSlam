import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileManip {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("mhyph_spaces.txt"));

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
