public class Sum3637 {
    public static void main(String[] args) {
        long counter = 0;
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isDigit(args[i].charAt(j)) || args[i].charAt(j) == '-') {
                    int l = j;
                    j += 1;
                    while (j < args[i].length() && Character.isDigit(args[i].charAt(j))){
                        j += 1;
                    }
                    counter += Long.parseLong(args[i].substring(l, j));
                    j -= 1;
                }
            }
        }
        System.out.println(counter);
    }
}
