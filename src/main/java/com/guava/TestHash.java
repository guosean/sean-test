package com.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * TODO description
 *
 * @author guozhenbin
 * @date 2020/1/29
 */
public class TestHash {

    @Test
    public void testBoolmFilter() {
        BloomFilter<String> bloomFilter = BloomFilter.create((Funnel<String>) (from, into) -> into.putString(from, Charset.defaultCharset()), 10);
        bloomFilter.put("tttt");
        bloomFilter.put("tttd");
        System.out.println(bloomFilter.mightContain("tttt"));
        System.out.println(bloomFilter.mightContain("tttd"));
    }

}
