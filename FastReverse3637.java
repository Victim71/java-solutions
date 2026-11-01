import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
;

public class FastReverse3637 {
    public static void main(String[] args) throws IOException {
        MyScanner lines = new MyScanner(new BufferedReader(new InputStreamReader(System.in)));
        int[][] matrix = new int[1][];
        int n = 0;
        while (lines.hasNext("line")) {
            String line = lines.next("line");
            MyScanner numbers = new MyScanner(new StringReader(line));
            int[] str = new int[1];
            int index = 0;
            while (numbers.hasNext("token")) {
                if (index == str.length) {
                    str = Arrays.copyOf(str,str.length * 2);
                }
                String k = numbers.next("token");
                if (k.length() >= 3 && k.charAt(0) == '0' && (k.charAt(1) == 'x' || k.charAt(1) == 'X')){
                    str[index] = Integer.parseUnsignedInt(k.substring(2), 16);
                }
                else{
                    str[index] = Integer.parseInt(k, 10);
                }
                index++;
            }

            if (matrix.length == n) {
                matrix = Arrays.copyOf(matrix, matrix.length * 2);
            }
            str = Arrays.copyOf(str, index);
            matrix[n] = str;
            n++;
        }

        matrix = Arrays.copyOf(matrix, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j > 0 && i > 0) {
                    if (j < matrix[i - 1].length) {
                        matrix[i][j] = matrix[i][j] + matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1];
                        continue;
                    }

                    else {
                        int t = 0;
                        int k;
                        for (k = i - 2; k > -1; k--) {
                            if (matrix[k].length > j) {
                                t = 1;
                                break;
                            }
                        }

                        if (t == 1) {
                            matrix[i][j] = matrix[i][j] + matrix[k][j] + matrix[i][j - 1] - matrix[k][j - 1];
                        } else {
                            matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
                        }
                        continue;
                    }
                }

                if (j == 0 && i == 0) {
                    continue;
                }

                if (j > 0) {
                    matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
                }

                else {
                    int k;
                    int t = 0;
                    for (k = i - 1; k > -1; k --) {
                        if (matrix[k].length > 0) {
                            t = 1;
                            break;
                        }
                    }

                    if (t == 1) {
                        matrix[i][j] = matrix[i][j] + matrix[k][j];
                    }
                }
            }
        }


        for (int i = 0; i < n; i++){
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("0x" + Integer.toHexString((matrix[i][j])));
                if (j + 1 < matrix[i].length){
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}