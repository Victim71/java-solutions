import java.io.*;
import java.util.*;

public class Wspp3637 {
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
        int numbers;
        Map<String, Integer> wordscnt2 = new HashMap<>();
        try {
            scan = new MyScanner(new InputStreamReader(new FileInputStream(in), "UTF-8"));
            String line = scan.next("line");
            while (line != null) {
                numbers = 0;
                MyScanner scan2 = new MyScanner(new StringReader(line));
                Map<String, Integer> wordscnt = new HashMap<>();
                String word = scan2.next("word3637");
                while (word != null) {
                    numbers++;
                    word = word.toLowerCase();
                    int val = wordscnt.getOrDefault(word, 0) + 1;
                    wordscnt.put(word, val);
                    cnt.putIfAbsent(word, new IntList())

                    IntList list = cnt.get(word);
                    wordscnt2.put(word, wordscnt2.getOrDefault(word, 0) + 1);
                    if (val % 2 == 1) {
                        if (list == null) {
                            list = new IntList();
                            list.add(numbers);
                            cnt.put(word, list);
                        } else {
                            list.add(numbers);
                            cnt.put(word, list);
                        }
                    }
                    word = scan2.next("word3637");
                }
                line = scan.next("line");
            }
        } catch (Exception e) {
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
        Comparator<String> reverse = new Comparator<String>() {
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        };
        List<String> keys = new ArrayList<>(cnt.keySet());
        keys.sort(reverse);
        try {
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));
            for (String key : keys){
                IntList list  = cnt.get(key);
                w.write(key + ' ' + wordscnt2.get(key));
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
