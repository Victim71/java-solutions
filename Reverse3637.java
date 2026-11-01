import java.util.Arrays;
import java.util.Scanner;

public class Reverse3637 {
    public Reverse3637() {
    }

    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        int[][] var2 = new int[1][];

        int var3;
        for(var3 = 0; var1.hasNextLine(); ++var3) {
            String var4 = var1.nextLine();
            Scanner var5 = new Scanner(var4);
            int[] var6 = new int[1];

            int var7;
            for(var7 = 0; var5.hasNext(); ++var7) {
                if (var7 == var6.length) {
                    var6 = Arrays.copyOf(var6, var6.length * 2);
                }

                var6[var7] = Integer.parseUnsignedInt(var5.next(), 16);
            }

            if (var2.length == var3) {
                var2 = (int[][])Arrays.copyOf(var2, var2.length * 2);
            }

            var6 = Arrays.copyOf(var6, var7);
            var2[var3] = var6;
        }

        var2 = (int[][])Arrays.copyOf(var2, var3);

        for(int var9 = 0; var9 < var3; ++var9) {
            for(int var11 = 0; var11 < var2[var9].length; ++var11) {
                if (var11 > 0 && var9 > 0) {
                    if (var11 < var2[var9 - 1].length) {
                        var2[var9][var11] = var2[var9][var11] + var2[var9 - 1][var11] + var2[var9][var11 - 1] - var2[var9 - 1][var11 - 1];
                    } else {
                        boolean var15 = false;

                        int var17;
                        for(var17 = var9 - 2; var17 > -1; --var17) {
                            if (var2[var17].length > var11) {
                                var15 = true;
                                break;
                            }
                        }

                        if (var15) {
                            var2[var9][var11] = var2[var9][var11] + var2[var17][var11] + var2[var9][var11 - 1] - var2[var17][var11 - 1];
                        } else {
                            var2[var9][var11] += var2[var9][var11 - 1];
                        }
                    }
                } else if (var11 != 0 || var9 != 0) {
                    if (var11 > 0) {
                        var2[var9][var11] += var2[var9][var11 - 1];
                    } else {
                        boolean var16 = false;

                        int var14;
                        for(var14 = var9 - 1; var14 > -1; --var14) {
                            if (var2[var14].length > 0) {
                                var16 = true;
                                break;
                            }
                        }

                        if (var16) {
                            var2[var9][var11] += var2[var14][var11];
                        }
                    }
                }
            }
        }

        for(int var10 = 0; var10 < var3; ++var10) {
            for(int var12 = 0; var12 < var2[var10].length; ++var12) {
                System.out.print(Integer.toHexString(var2[var10][var12]));
                if (var12 + 1 < var2[var10].length) {
                    System.out.print(' ');
                }
            }

            System.out.println();
        }

    }
}
