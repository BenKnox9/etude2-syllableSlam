import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TestMap {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/danielbohinc/Documents/Sylable-Slam/syllable_slam/mhyph_spaces.txt");
        Scanner sc = new Scanner(f);
        HashSet<String> syllableSplits = new HashSet<String>();
        ArrayList<String> arr = new ArrayList<>();
        String[] s;

        while(sc.hasNextLine()) {

            s = sc.nextLine().split("[ \\-\\/]");
            for(String str : s )arr.add(str);
        }
        syllableSplits.addAll(arr);
    }
}
