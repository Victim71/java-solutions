import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.min;

public class WordStat3637 {
    public static void createsuf(Map<String, Integer> cnt, int wordlen, char [] wordchar){
        if (wordlen > 0){
            String maxstr = new String(wordchar, 0, wordlen);
            int maxsize = min(wordlen, 100);
            for (int p = maxsize; p > 0; p --){
                String wordstr = maxstr.substring(wordlen - p, wordlen);
                // Use merge instead this
                if(cnt.containsKey(wordstr)){
                    cnt.put(wordstr, cnt.get(wordstr) + 1);
                }
                else{
                    cnt.put(wordstr, 1);
                }
            }
        }
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.print("Error");
            return;
        }
        char[] buffer = new char[4096];
        char[] wordchar = new char[32];
        int wordlen = 0;
        String in = args[0];
        String out = args[1];
        Map<String, Integer> cnt = new HashMap<>();
        try {
            Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(in), "UTF-8"));
            int n;
            while ((n = r.read(buffer)) != -1) {
                for (int i = 0; i < n; i++) {
                    char k = buffer[i];
                    if (Character.isLetter(k) || Character.getType(k) == Character.DASH_PUNCTUATION || k == '\'') {
                        if (wordlen > wordchar.length - 1) {
                            wordchar = Arrays.copyOf(wordchar, wordchar.length * 2);
                        }
                        wordchar[wordlen] = Character.toLowerCase(k);
                        wordlen++;
                    } else {
                        createsuf (cnt, wordlen, wordchar);
                        wordlen = 0;
                    }
                }
            }
            r.close();
            createsuf (cnt, wordlen, wordchar);
        }
        catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
        Writer writer;
        try {
            String[] keys = cnt.keySet().toArray(new String[0]);
            Arrays.sort(keys, new Comparator<String>(){
                public int compare (String s1, String s2) {
                    int srav = Integer.compare(cnt.get(s1), cnt.get(s2));
                    if (srav != 0){
                        return srav;
                    }
                    return s1.compareTo(s2);
                }
            });
            Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
            for (int j = 0; j < keys.length; j ++){
                w.write(keys[j] + " " + cnt.get(keys[j]));
                    if (j < keys.length - 1){
                    w.write(System.lineSeparator());
                }
            }
            w.close();
        }
        catch (IOException a) {
            System.err.println("Error" + a.getMessage());
        } finally {

        }
    }
}
