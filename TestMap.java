import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TestMap {

    public static void main(String[] args) throws FileNotFoundException {
        // File f = new File("/Users/danielbohinc/Documents/Sylable-Slam/syllable_slam/mhyph_spaces.txt");
        // Scanner sc = new Scanner(f);
        // HashSet<String> syllableSplits = new HashSet<String>();
        // ArrayList<String> arr = new ArrayList<>();
        String[] s;

        // while(sc.hasNextLine()) {

        //     s = sc.nextLine().split("[ \\-\\/]");
        //     for(String str : s )arr.add(str);
        // }
        // syllableSplits.addAll(arr);
        // sc.close();

        File file = new File("/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/25kwords.txt");
        FileOutputStream fout =new FileOutputStream("/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/25output.txt");
        FileOutputStream replaceOut =new FileOutputStream("/Users/danielbohinc/Documents/SyllableSlam/syllable_slam/25kwordsclean.txt");
        PrintWriter pw = new PrintWriter(fout, true);
        PrintWriter pw1 = new PrintWriter(replaceOut, true);
        Scanner scan = new Scanner(file);
        int count = 0;
        String str = "";

        while(scan.hasNextLine()) {
            count++;
            s = scan.nextLine().split(";");
            pw.println(s.length);
        }
        scan.close();

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            str = sc.nextLine().replace(";", "");
            pw1.println(str);
        }
        pw1.close();
        sc.close();
        pw.close();
        System.out.println(count);
    }   
}
