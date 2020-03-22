package com.sean.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * 对于一个数组，输出出现频率最高的前K个数
 *
 * @author guozhenbin
 * @date 2020/3/20
 */
public class TopK {

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 2, 3, 1, 4, 3, 5, 5, 2, 2, 2, 4};
        int k = 5;
        int[] result = findTopK(array, k);
        IntStream.of(result).forEach(i -> System.out.println(i));
    }

    public static int[] findTopK(int[] array, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(array.length);
        int[] result = new int[k];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        //计数
        IntStream.of(array).forEach(i -> {
            Integer value = map.computeIfPresent(i, (k1, v) -> v + 1);
            if (null == value) {
                map.put(i, 1);
            }
        });
        //排序
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        //取值
        for (int j = 0; j < k; j++) {
            Node node = pq.poll();
            result[j] = node.value;
        }

        return result;
    }

    private static class Node {

        private Integer value;
        private Integer weight;

        Node(Integer value, Integer weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "value:" + value + ",weight:" + weight;
        }

        @Override
        public boolean equals(Object node) {

            if (!(node instanceof Node) || null == value) {
                return false;
            }

            return this.value.equals(((Node) node).value);
        }

    }
}
