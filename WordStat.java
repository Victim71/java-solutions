    import java.io.*;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Map;

    public class WordStat {
        public static void main(String[] args) {
            if (args.length < 2) {
                System.out.print("Error");
                return;
            }
            char[] buffer = new char[4096];
            char[] wordchar = new char[32];
            String[] words = new String[64];
            int countwords = 0;
            int wordlen = 0;
            String in = args[0];
            String out = args[1];
            Map<String, Integer> cnt = new HashMap<>();
            int numbers;
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
                            if (wordlen > 0) {
                                String wordstr = new String(wordchar, 0, wordlen);
                                if (cnt.containsKey(wordstr)) {
                                    numbers = cnt.get(wordstr);
                                    cnt.put(wordstr, numbers + 1);
                                } else {
                                    cnt.put(wordstr, 1);
                                    if (countwords > words.length - 1) {
                                        words = Arrays.copyOf(words, words.length * 2);
                                    }
                                    words[countwords] = wordstr;
                                    countwords++;
                                }
                            }
                            wordlen = 0;
                        }
                    }
                }
                r.close();
                if (wordlen > 0) {
                    String last = new String(wordchar, 0, wordlen);
                    if (cnt.containsKey(last)) {
                        numbers = cnt.get(last);
                        cnt.put(last, numbers + 1);
                        if (countwords > words.length - 1) {
                            words = Arrays.copyOf(words, words.length * 2);
                        }
                        words[countwords] = last;
                    }
                    else {
                        cnt.put(last, 1);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));
                for (int j = 0; j < countwords; j ++){
                    w.write(words[j] + ' ' + cnt.get(words[j]) + "\n");
                }
                w.close();
            }
            catch (Exception a) {
                a.printStackTrace();
            }
        }
    }
