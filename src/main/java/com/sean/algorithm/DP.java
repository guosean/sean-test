package com.sean.algorithm;

import org.apache.logging.log4j.util.Strings;

/**
 * 动态规划
 *
 * @author guozhenbin
 * @date 2020/2/4
 */
public class DP {

    public static void main(String[] args) {
        String str1 = "fishes";
        String str2 = "fill";
        String str3 = "dish";
        System.out.println(maxCommonStrLen(str1, str3));
    }

    /**
     * 最长公共子串
     * @param s1
     * @param s2
     * @return
     */
    private static int maxCommonStrLen(final String s1, final String s2) {
        int maxLen = 0;

        if (Strings.isBlank(s1) || Strings.isBlank(s2)) {
            return maxLen;
        }

        int[][] metrix = new int[s2.length()][s1.length()];

        for (int i = 0; i < s2.length(); i++) {
            char c1 = s2.charAt(i);
            for (int j = 0; j < s1.length(); j++) {
                char c2 = s1.charAt(j);
                if (c1 != c2) {
                    metrix[i][j] = 0;
                } else {
                    metrix[i][j] = 1 + (i < 1 ? 1 : (metrix[i - 1][j] + (j < 1 ? 0 : metrix[i - 1][j - 1])));
                    System.out.println("mextri[i][j]" + i + "," + j + "," + metrix[i][j]);
                    maxLen = maxLen <= metrix[i][j] ? metrix[i][j] : maxLen;
                }
            }
        }

        for (int i = 0; i < metrix.length; i++) {
            int[] ar1 = metrix[i];
            for (int j = 0; j < ar1.length; j++) {
                System.out.print(String.format("%d,", metrix[i][j]));
            }
            System.out.println(" ");
        }
        return maxLen;
    }
}
