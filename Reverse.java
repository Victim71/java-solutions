import java.util.Scanner;

public class Reverse {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String [] lines = new String [1_000_000];
        int n = 0;
        while (scan.hasNextLine()){
            lines[n]= scan.nextLine();
            n++;
        }
        for (int i = n - 1; i >= 0; i--) {
            String line = lines[i];
            Scanner scline1 = new Scanner(line);
            int x = 0;
            while (scline1.hasNextInt()) {
                scline1.nextInt();
                x += 1;
            }
            int[] numbers = new int[x];
            x--;
            Scanner scline2 = new Scanner(line);
            while (scline2.hasNextInt()) {
                numbers[x] = scline2.nextInt();
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
