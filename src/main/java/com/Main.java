package com;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-23
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        readSkuAndLec();
        readSkuAndSub();
        /*String inFileName = String.format("/Users/guozhenbin/tmp/0107");
        BufferedReader reader = new BufferedReader(new FileReader(inFileName));
        String lineStr = null;//单行数据

        Map<String, List<String>> maps = Maps.newHashMap();
        List<String> code = Lists.newArrayList();
        int num = 0;
        do {
            lineStr = reader.readLine();
            if(++num%2 == 1 && Objects.nonNull(lineStr)){
                code.add(lineStr);
            }
        }while (null != lineStr);
        System.out.println(Joiner.on("\",\"").join(code));*/
    }

    public static void readSkuAndLec() throws IOException {
        String inFileName = String.format("/Users/guozhenbin/tmp/lec_sku");
        BufferedReader reader = new BufferedReader(new FileReader(inFileName));
        String lineStr = null;//单行数据

        Map<String, List<String>> maps = Maps.newHashMap();
        do {
            lineStr = reader.readLine();
            if (null != lineStr) {
                List<String> list = Splitter.on("\t").splitToList(lineStr);
                if (list.size() == 2) {
                    String sku = list.get(1);
                    String sub = list.get(0);
                    List<String> subList = maps.get(sku);
                    if (Objects.isNull(subList)) {
                        subList = Lists.newLinkedList();
                    }
                    subList.add(sub);
                    maps.put(sku, subList);
                }
            }
        } while (null != lineStr);
        System.out.println(JSON.toJSONString(maps));
    }

    public static void readSkuAndSub() throws IOException {
        String inFileName = String.format("/Users/guozhenbin/tmp/sub_sku");
        String outFileName = String.format("/Users/guozhenbin/tmp/sub_sku_rt");
        BufferedReader reader = new BufferedReader(new FileReader(inFileName));
        String lineStr = null;//单行数据

        Map<String, List<String>> maps = Maps.newHashMap();
        do {
            lineStr = reader.readLine();
            if (null != lineStr) {
                List<String> list = Splitter.on("\t").splitToList(lineStr);
                if (list.size() == 2) {
                    String sku = list.get(0);
                    String sub = list.get(1);
                    List<String> subList = maps.get(sku);
                    if (Objects.isNull(subList)) {
                        subList = Lists.newLinkedList();
                    }
                    subList.add(sub);
                    maps.put(sku, subList);
                }
            }
        } while (null != lineStr);
        System.out.println(JSON.toJSONString(maps));
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();

    }

    public static String formatTime(TimeUnit unit, long time) {
        long hours = 0;
        long totalMinutes = unit.toMinutes(time);
        long minutes = totalMinutes;
        if (totalMinutes > 0) {
            hours = unit.toHours(time);
        }
        if (hours > 0) {
            minutes = totalMinutes - (TimeUnit.HOURS.toMinutes(hours));
        }

        return String.format("%d小时%d分", hours, minutes);
    }

    public static int findIdexOfNum(int[] array, int num) {
        if (null == array || array.length == 0) {
            return -1;
        }

        int len = array.length;
        int idx, low, mid = len / 2, high;
        low = array[mid] > num ? 0 : mid;
        high = low > mid ? len : mid;
        idx = findIndexOfNum(array, num, low, high);

        while (idx > 0 && array[idx - 1] == num) {
            mid = idx / 2;
            high = idx;
            low = array[mid] > num ? 0 : mid;
            idx = findIndexOfNum(array, num, low, high);
        }

        return idx;
    }

    private static int findIndexOfNum(int[] array, int num, int low, int high) {
        if (low == high && array[low] != num) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (array[mid] == num) {
            return mid;
        } else if (array[mid] > num) {
            return findIndexOfNum(array, num, low, mid);
        } else {
            return findIndexOfNum(array, num, mid, high);
        }

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