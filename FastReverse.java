import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class FastReverse {
    public static void main(String[] args) throws IOException {
        MyScanner scan = new MyScanner(new InputStreamReader(System.in));
        String [] lines = new String [1_000_000];
        int n = 0;
        while (scan.hasNext("line")){
            lines[n]= scan.next("line");
            n++;
        }
        for (int i = n - 1; i >= 0; i--) {
            String line = lines[i];
            MyScanner scline1 = new MyScanner(new StringReader(line));
            int x = 0;
            while (scline1.hasNext("int")) {
                scline1.next("int");
                x += 1;
            }
            int[] numbers = new int[x];
            x--;
            MyScanner scline2 = new MyScanner(new StringReader(line));
            while (scline2.hasNext("int")) {
                numbers[x] = Integer.parseInt(scline2.next("int"));
                x--;
            }
            int j = 0;
            while (j < numbers.length) {
                System.out.print(numbers[j]);
                j++;
                if (j - 1 < numbers.length) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
