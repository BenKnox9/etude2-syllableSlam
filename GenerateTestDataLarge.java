
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class to generate testdata_large files i.e. testdata_large_in and
 * testdata_large_out for testing countSyllables class
 * 
 * @author Daniel Bohnic
 */
public class GenerateTestDataLarge {

    public static void main(String[] args) throws FileNotFoundException {

        String[] s;
        String origTestFile = "testdata_large.txt";
        String testFileIn = "testdata_large_in.txt";
        String testFileOut = "testdata_large_out.txt";
        File file = new File("/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/" + origTestFile);
        FileOutputStream fout = new FileOutputStream(
                "/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/" + testFileOut);
        FileOutputStream fin = new FileOutputStream(
                "/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/" + testFileIn);
        PrintWriter pw1 = new PrintWriter(fout, true);
        PrintWriter pw2 = new PrintWriter(fin, true);
        Scanner scan = new Scanner(file);
        int count = 0;
        String str = "";

        while (scan.hasNextLine()) {
            count++;
            s = scan.nextLine().split(";");
            pw1.println(s.length);
        }
        scan.close();

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            str = sc.nextLine().replace(";", "");
            pw2.println(str);
        }
        pw2.close();
        sc.close();
        pw1.close();
        System.out.println(count);
    }
}
