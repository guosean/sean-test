package com.sean.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestString {
	@Test
	public void testJoin(){
		Assert.assertEquals("1,2", Joiner.on(',').join(Arrays.asList(1,2)));
		Assert.assertEquals("1,2", Joiner.on(',').skipNulls().join(Arrays.asList(1,2,null)));
	}
	@Test
	public void testSplit(){
		String str = "1,,2,";
		Assert.assertEquals(3, str.split(",").length);
//		Assert.assertEquals(4, Splitter.on(',').splitToList(str).size());
//		Assert.assertEquals(2, Splitter.on(',').omitEmptyStrings().splitToList(str).size());
	}
    @Test
	public void testCharMatcher(){
		String str = "1ab#@|,.A";
		Assert.assertEquals("a", CharMatcher.isNot('a').removeFrom(str));
	}
    @Test
    public void testCaseFormat(){
    	Assert.assertEquals("constant-name",CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME"));
    }
	
}
