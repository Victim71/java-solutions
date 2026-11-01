import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.print("Error");
            return;
        }
        MyScanner scan = null;
        Writer w = null;
        String in = args[0];
        String out = args[1];
        Map<String, IntList> cnt = new LinkedHashMap<>();
        int numbers = 0;
        try {scan = new MyScanner(new InputStreamReader(new FileInputStream(in), "UTF-8"));
            String word = scan.next("word");
            while (word != null){
                numbers++;
                word = word.toLowerCase();
                IntList list = cnt.get(word);
                if (list == null){
                    list = new IntList();
                    list.add(numbers);
                    cnt.put(word, list);
                }
                else {
                    list.add(numbers);
                    cnt.put(word, list);
                }
                word = scan.next("word");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (scan != null){
            try {
                scan.closeScanner();
            }
            catch (Exception c) {
                c.printStackTrace();
            }
        }
        try {
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));
            for (String key : cnt.keySet()){
                IntList list  = cnt.get(key);
                w.write(key + ' ' + list.size());
                for (int i = 0; i < list.size(); i++){
                    w.write(" " + list.get(i));
                }
                w.write("\n");
            }
        }
        catch (Exception a) {
            a.printStackTrace();
        }
        if (w != null){
            try {
                w.close();
            }
            catch (Exception c) {
                c.printStackTrace();
            }
        }
    }
}
