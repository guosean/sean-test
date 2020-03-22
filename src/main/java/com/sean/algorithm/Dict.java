package com.sean.algorithm;

/**
 * TODO description
 *
 * @author guozhenbin
 * @date 2020/3/5
 */
public class Dict {

    public static void main(String[] args) {
        System.out.println(findKthNumber(13,2));
    }

    public static int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            long step = 0, first = cur, last = cur + 1;
            while (first <= n) {
                step += Math.min(last, (long) (n + 1)) - first;
                first *= 10;
                last *= 10;
            }

            if (step > k) {
                //在树里
                k--;
                cur *= 10;
            }
            if (step <= k) {
                //不在树里
                k -= step;
                ++cur;
            }
        }
        return cur;
    }
}
