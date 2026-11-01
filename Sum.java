public class Sum {
    public static void main(String[] args) {
        int counter = 0;
        for (int i=0; i < args.length; i++) {
            for (int j=0; j < args[i].length(); j++) {
                if (Character.isDigit(args[i].charAt(j))) {
                    int l = j;
                    int r = j;
                    while(j < args[i].length() && Character.isDigit(args[i].charAt(j))){
                        r = j;
                        j += 1;
                }
                    String number = args[i].substring(l,r+1);
                    counter += Integer.parseInt(number);
                    j -= 1;
                    continue;
                }
                if (args[i].charAt(j) == '-') {
                    j += 1;
                    int l = j;
                    int r = j;
                    while(j < args[i].length() && Character.isDigit(args[i].charAt(j))) {
                        r = j;
                        j += 1;
                    }
                    String number = args[i].substring(l,r+1);
                    counter -= Integer.parseInt(number);
                    j -= 1;
                    continue;
                }
                if (args[i].charAt(j) == '+') {
                    j += 1;
                    int l = j;
                    int r = j;
                    while(j < args[i].length() && Character.isDigit(args[i].charAt(j))) {
                        r = j;
                        j += 1;
                    }
                    String number = args[i].substring(l,r+1);
                    counter += Integer.parseInt(number);
                    j -= 1;
                    continue;
                }
            }
        }
        System.out.println(counter);
    }
}
