import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class MyScanner {
    Reader reader;
    char[] buffer = new char[4096];
    int bufferlenght = 0;
    int bufferpos = 0;
    char[] wordchar = new char[32];
    public MyScanner(Reader reader){
        this.reader = reader;
    }
    boolean is(char a, String function){
        if (function.equals("int")){
            return a != ' ' && a != '\t' && a != '\n' && a != '\r' && a != '(' && a!=')' && Character.getType(a) != Character.START_PUNCTUATION && Character.getType(a) != Character.END_PUNCTUATION;
        }
        if (function.equals("token")){
            return a != ' ' && a != '\t' && a != '\n' && a != '\r' && Character.getType(a) != Character.START_PUNCTUATION && Character.getType(a) != Character.END_PUNCTUATION;
        }
        if (function.equals("line")){
            return a != '\n' && a != '\r';
        }
        if (function.equals("word")){
            return Character.isLetter(a) || Character.getType(a) == Character.DASH_PUNCTUATION || a == '\'';
        }
        if (function.equals("word3637")){
            return Character.isLetter(a) || Character.getType(a) == Character.DASH_PUNCTUATION || a == '\'' || Character.getType(a) == Character.CURRENCY_SYMBOL;
        }
        return false;
    }
    public String next(String function) throws IOException{
        if (!function.equals("line")){
            while (bufferlenght != -1){
                if (bufferpos >= bufferlenght){
                    bufferlenght = reader.read(buffer);
                    bufferpos = 0;
                    if (bufferlenght == -1){
                        break;
                    }
                }
                char a = buffer[bufferpos];
                if (is(a, function)){
                    break;
                }
                bufferpos++;
            }
        }
        if (bufferpos >= bufferlenght) {
            bufferlenght = reader.read(buffer);
            bufferpos = 0;
        }
        if (bufferlenght == -1) {
            return null;
        }
        int lenght = 0;
        while (bufferlenght != -1){
            if (bufferpos >= bufferlenght){
                bufferlenght = reader.read(buffer);
                bufferpos = 0;
                if (bufferlenght == -1){
                    break;
                }
            }
            char a = buffer[bufferpos];
            if (!is(a,function)) {
                if (function.equals("line")){
                    if (a =='\r'){
                        bufferpos++;
                        if (bufferpos >= bufferlenght && bufferlenght != -1){
                            bufferlenght = reader.read(buffer);
                            bufferpos = 0;
                        }
                        if (bufferlenght != -1 && buffer[bufferpos] == '\n'){
                            bufferpos++;
                        }
                    }
                    if (a =='\n'){
                        bufferpos++;
                    }
                }
                break;
            }
            if (lenght == wordchar.length){
                wordchar = Arrays.copyOf(wordchar,wordchar.length * 2);
            }
            wordchar[lenght] = a;
            lenght++;
            bufferpos++;
        }
        return new String(wordchar, 0, lenght);
    }
    public boolean hasNext(String function) throws IOException{
        if (bufferpos >= bufferlenght) {
            bufferlenght = reader.read(buffer);
            bufferpos = 0;
        }
        if (function.equals("line")){
            return bufferlenght != -1;
        }
        while (bufferlenght != -1){
            if (bufferpos >= bufferlenght){
                bufferlenght = reader.read(buffer);
                bufferpos = 0;
                if (bufferlenght == -1){
                    break;
                }
            }
            char a = buffer[bufferpos];
            if (is(a, function)){
                return true;
            }
            bufferpos ++;
        }
        return false;
    }
    public void closeScanner() throws IOException {
        reader.close();
    }
}
