package com;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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
        List<Integer> skuList = Lists.newArrayList(231, 238, 270, 340, 200, 266, 219, 282, 285, 315, 304, 346, 295, 323, 335);
        List<String> titleList = Lists.newArrayList("{lec_name}老师的《{course_name}》正在直播，快来看看吧>>",
                "{lec_name}老师的《{course_name}》开始了", "跟{sku}老师一起学习《{course_name}》，进来看看吧",
                "精选{sku}课：《{course_name}》", "一起来看《{course_name}》",
                "我在学习《{course_name}》，一起来看吧");
        List<String> textList = Lists.newArrayList("大家好，{lec_name}老师的{sku}公开课正在直播，快来看看吧~\\n\\n课程名称：{course_name}\\n\\n授课讲师：{lec_name}·{lec_title}\\n\\n点击上方小程序，与老师同学一起互动学习~", "【上课通知】今日推荐的{sku}公开课开播啦\\n\\n课程名称：{course_name}\\n\\n{lec_name}老师：{lec_title}\\n\\n点击上方小程序，马上进入直播间~");
//        System.out.println(titleList);
        String textInsertFmt = "INSERT INTO `hs_ad_material_unit` (`unit_type`, `material_content_type`, `material_type`, `content`, `sku_id`, `create_user`)\n" +
                "VALUES\n" +
                "\t('groupText', 'text', 4, '%s', %s, 'zhenbin.guo');";
        String titleInsertFmt = "INSERT INTO `hs_ad_material_unit` (`unit_type`, `material_content_type`, `material_type`, `content`, `sku_id`, `create_user`)\n" +
                "VALUES\n" +
                "\t('mpCardTitle', 'miniprogrampage', 4, '%s', %s, 'zhenbin.guo');";
        skuList.forEach(sku -> {
            titleList.forEach(tit -> {
                System.out.println(String.format(titleInsertFmt, tit, sku));
            });
            textList.forEach(txt -> {
                System.out.println(String.format(textInsertFmt, txt, sku));
            });
        });
//        readSkuAndLec();
//        readSkuAndSub();
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
        String inFileName = String.format("/Users/guozhenbin/tmp/sku");

        BufferedReader reader = new BufferedReader(new FileReader(inFileName));
        String lineStr = null;//单行数据
        List<String> skuMapList = Lists.newArrayList("写意画", "11", "英语", "13", "旅游", "17", "民歌", "18", "楷书", "21", "瑜伽", "23", "电子琴", "24", "葫芦丝", "26", "工笔画", "30", "太极", "33", "京剧", "38", "钢琴", "41", "古筝", "42", "视唱练耳", "45", "养生", "49", "二胡", "52", "彩铅", "62", "中医", "67", "口琴", "83", "中国古典舞", "416", "行书", "425", "美声", "431", "通俗", "432", "文学朗诵", "447", "练唱室", "515");
        List<String> skuIndexList = Lists.newArrayList("民歌", "写意画", "钢琴", "美声", "视唱练耳", "楷书", "葫芦丝", "通俗", "养生", "文学朗诵", "二胡", "练唱室", "古筝", "工笔画", "英语", "电子琴", "京剧", "彩铅", "中医", "行书", "太极", "瑜伽", "中国古典舞", "旅游", "口琴");
        Map<String, String> skuMap = Maps.newHashMap();
        for (int i = 0; i < skuMapList.size(); i = i + 2) {
            skuMap.put(skuMapList.get(i), skuMapList.get(i + 1));
        }
        System.out.println(skuMap);
        Map<String, List<HsCategorySimpleDTO>> maps = Maps.newHashMap();
        int i = 0;
        List<String> noSku = Lists.newArrayList("45", "515");
        do {
            lineStr = reader.readLine();
            String lineSku = skuMap.get(skuIndexList.get(i));
            List<HsCategorySimpleDTO> relativeSku = Lists.newLinkedList();
            DecimalFormat df = new DecimalFormat("0.00");
            if (null != lineStr && !noSku.contains(lineSku)) {

                List<String> list = Splitter.on("\t").splitToList(lineStr);
                for (int j = 0; j < list.size(); j++) {
                    if (StringUtils.equalsIgnoreCase(list.get(j), "null")) {
                        continue;
                    }
                    try {
                        Double score = Double.valueOf(list.get(j));
                        HsCategorySimpleDTO dto = new HsCategorySimpleDTO();
                        if (noSku.contains(skuMap.get(skuIndexList.get(j)))) {
                            continue;
                        }
                        dto.setId(Integer.valueOf(skuMap.get(skuIndexList.get(j))));
                        if (score > 0) {
                            dto.setRelativeScore(Double.valueOf(df.format(score)));
                            relativeSku.add(dto);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(list);
            }
            maps.put(lineSku, relativeSku);
            i++;
        } while (null != lineStr && i < 25);
        System.out.println(JSON.toJSONString(maps));
    }

    @Data
    public static class HsCategorySimpleDTO {
        private Integer id;
        /**
         * 相关性系数
         */
        private Double relativeScore;
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