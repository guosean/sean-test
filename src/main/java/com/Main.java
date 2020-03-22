package com;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-23
 */
public class Main {

    public static void main(String args[]) {
       /* HashMap<Object, Object> map = Maps.newHashMap();
        map.put("1","2");
        map.put("2","3");
        map.forEach((k,v)->{
            System.out.println(String.format("k:%s,v:%s",k,v));
        });
        int i = 10;
        System.out.println(Integer.valueOf(i).byteValue());
//        System.out.println(lengthOfLongestSubstring("dvdf"));
        for(i=0; i<3; i++){
            caculRebalance(i+1);
        }*/

        System.out.println(reverseNum(-321));
    }

    public static int reverseNum(int num) {
        int ans = 0;
        int flag = num > 0 ? 1 : -1;
        num = Math.abs(num);

        while (num != 0) {
            ans = ans * 10 + num % 10;
            num /= 10;
        }

        return flag * ans;
    }

    public static void caculRebalance(int currentCid) {
        List<Integer> cidAll = Lists.asList(1, 2, new Integer[]{3});
        int currentCID = currentCid;
        int index = cidAll.indexOf(currentCID);
        List<String> mqAll = Lists.asList("mq1", "mq2", new String[]{"mq3", "mq4"});
        int mod = mqAll.size() % cidAll.size();
        System.out.println("mod:" + mod);
        int averageSize =
                mqAll.size() <= cidAll.size() ? 1 : (mod > 0 && index < mod ? mqAll.size() / cidAll.size()
                        + 1 : mqAll.size() / cidAll.size());
        System.out.println("averageSize:" + averageSize);
        int startIndex = (mod > 0 && index < mod) ? index * averageSize : index * averageSize + mod;
        System.out.println("startIndex:" + startIndex);
        int range = Math.min(averageSize, mqAll.size() - startIndex);
        List<String> result = Lists.newArrayList();
        System.out.println("range:" + range);
        for (int i = 0; i < range; i++) {
            result.add(mqAll.get((startIndex + i) % mqAll.size()));
        }
        System.out.println(result);
    }

    public static int[] quickSort(int[] array) {
        int[] result = array;

        return result;
    }

    public static void quickSort(int[] array, int start, int end) {

    }

    public static int lengthOfLongestSubstring(String s) {
        Set set = new HashSet();
        int startIdx = 0;
        int endIdx = 0;
        char chr;
        int maxTotal = 0;
        int len = s.length();
        while (startIdx < len && endIdx < len) {
            chr = s.charAt(endIdx);
            if (!set.contains(chr)) {
                endIdx++;
                set.add(chr);
                maxTotal = Math.max(maxTotal, endIdx - startIdx);
            } else {
                set.remove(s.charAt(startIdx++));
            }
        }

        return maxTotal;

    }

    public static int candy(int[] ratings) {
        int tg = 0;
        if (null == ratings || ratings.length == 0) {
            return tg;
        }
        int students = ratings.length;
        tg = students;
        for (int i = 0; i < students - 1; i++) {
            if (ratings[i] > ratings[i + 1]) {
                tg++;
            }
        }
        return tg;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = l1, q = l2, curr = result;
        int addNum = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + addNum;
            addNum = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (addNum > 0) {
            curr.next = new ListNode(addNum);
        }
        return result;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}