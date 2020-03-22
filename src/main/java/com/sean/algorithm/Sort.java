package com.sean.algorithm;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.Random;

public class Sort {

    public static void main(String[] args) {
		/*int[] arrays = new int[10];
		generateRandomArray(arrays);
		System.out.println(Arrays.toString(arrays));
		 bubbleSort(arrays);
		 System.out.println(Arrays.toString(arrays));
		 generateRandomArray(arrays);
		 System.out.println(Arrays.toString(arrays));
		 insertSort(arrays);
		 System.out.println(Arrays.toString(arrays));
		 generateRandomArray(arrays);
		 System.out.println(Arrays.toString(arrays));
		 mergeSort(arrays, 0, arrays.length - 1);
		 System.out.println(Arrays.toString(arrays));
		 generateRandomArray(arrays);
		 System.out.println(Arrays.toString(arrays));
		quickSort(arrays, 0, arrays.length - 1);
		System.out.println(Arrays.toString(arrays));*/

        LinkedList<Integer> left = Lists.newLinkedList(Lists.asList(1, 3, new Integer[]{5}));
        LinkedList<Integer> right = Lists.newLinkedList(Lists.asList(2, 4, new Integer[]{6}));
        System.out.println(mergeSortedList(left, right));
    }

    static LinkedList<Integer> mergeSortedList(LinkedList left, LinkedList right) {

        if (null == left || null == right) {
            return null == left ? right : left;
        }
        int leftLen = left.size();
        int rightLen = right.size();
        boolean leftBigger = leftLen >= rightLen;
        LinkedList<Integer> resultList = Lists.newLinkedList(leftBigger ? left : right);
        LinkedList<Integer> loopList = leftBigger ? right : left;
        int i = 0;
        int j = 0;

        while (i < loopList.size() && j < resultList.size()) {
            int loopValue = loopList.get(i);
            int lastValue = resultList.get(j);
            if (loopValue <= lastValue) {
                i++;
                resultList.add(j, loopValue);
            } else {
                j++;
            }
        }

        if (i == loopList.size() - 1) {
            resultList.add(loopList.get(i));
        }

        return resultList;
    }

    static void generateRandomArray(int[] arrays) {
        Random rd = new Random();
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = rd.nextInt(10);
        }
    }

    /**
     * 冒泡排序
     *
     * @param arrays
     */
    public static void bubbleSort(int[] arrays) {
        if (null != arrays) {
            int len = arrays.length;
            for (int i = len; i > 0; i--) {
                for (int j = 0; j < i - 1; j++) {
                    if (arrays[j + 1] < arrays[j]) {
                        swap(arrays, j, j + 1);
                    }
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arrays
     */
    public static void insertSort(int[] arrays) {
        if (null != arrays) {
            int len = arrays.length;
            for (int i = 1; i < len; i++) {
                for (int j = i; j > 0; j--) {
                    if (arrays[j] < arrays[j - 1]) {
                        swap(arrays, j - 1, j);
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arrays, int lo, int hi) {
        if (lo < hi) {
            int mid = getQuickMid(arrays, lo, hi);
            quickSort(arrays, lo, mid - 1);
            quickSort(arrays, mid + 1, hi);
        }
    }

    private static int getQuickMid(int[] arrays, int lo, int hi) {
        int tmp = arrays[lo];
        while (lo < hi) {
            while (lo < hi && arrays[hi] >= tmp) {
                hi--;
            }
            arrays[lo] = arrays[hi];
            while (lo < hi && arrays[lo] <= tmp) {
                lo++;
            }
            arrays[hi] = arrays[lo];
        }
        arrays[lo] = tmp;
        return lo;
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] arrays, int lo, int hi) {
        if (lo < hi) {
            int mid = (hi + lo) / 2;
            mergeSort(arrays, lo, mid);
            mergeSort(arrays, mid + 1, hi);
            merge(arrays, lo, mid, hi);
        }
    }

    private static void merge(int[] arrays, int lo, int mid, int hi) {
        int[] tmp = new int[arrays.length];
        int k = lo;
        int tmpi = lo;
        int i = lo;
        int j = mid + 1;
        while (i <= mid && j <= hi) {
            if (arrays[i] <= arrays[j]) {
                tmp[k++] = arrays[i++];
            } else {
                tmp[k++] = arrays[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arrays[i++];
        }
        while (j <= hi) {
            tmp[k++] = arrays[j++];
        }

        while (tmpi <= hi) {
            arrays[tmpi] = tmp[tmpi++];
        }
    }

    public static void swap(int[] arrays, int i, int j) {
        if (null != arrays) {
            int len = arrays.length;
            if (i >= 0 && j >= 0 && i < len && j < len) {
                int tmp = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] = tmp;
            }
        }
    }

}
