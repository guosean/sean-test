package com.sean.datamining;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.base.Preconditions;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-12
 */
public class VSM {
	@Test
	public void testStringDistance(){
		String str1 = "中国";
		String str2 = "中国人";
		Assert.assertEquals(StringUtils.getLevenshteinDistance(str1, str2), 1);
	}
	@Test
	public void testCalVSM(){
		String[][] rules = {{"梦网:5","短信:3"},{"梦网:5","彩信:3"},{"国际:5","短信:3"}};
		String[] fts = {"梦网:1","短信息:1","问题:1","彩信:1"};
		List<Map<String,Integer>> ruleList = new ArrayList<Map<String,Integer>>();
		
		for(String[] ruleScore:rules){
				ruleList.add(transToMap(ruleScore));
		}
	
		Map<String,Integer> ftMap = transToMap(fts);
		for (Map<String, Integer> map : ruleList) {
			System.out.println(map.toString());
			System.out.println(calCos(map, ftMap));
		}
	}
	
	public Map<String,Integer> transToMap(final String[] ruleScore){
		Map<String, Integer> rl = new HashMap<String, Integer>();
		for(String rule:ruleScore){
			String[] rs = StringUtils.splitByWholeSeparator(rule, ":");
			rl.put(rs[0], Integer.valueOf(rs[1]));
		}
		return rl;
	}
	
    public double calCos(Map<String,Integer> rule,Map<String,Integer> features){
    	Preconditions.checkNotNull(rule);
    	Preconditions.checkNotNull(features);
    	Set<String> ruleKeys = rule.keySet();
    	double sumOfPro = 0;//乘积之和
    	double sumOfRule = 0;
    	double sumOfFt = 0;
    	int ftValue = 0;
    	int ruleValue = 0;
    	for (String key : ruleKeys) {
    		if(features.containsKey(key)){
    			ftValue = features.get(key);
    			ruleValue = rule.get(key);
    			sumOfPro += ruleValue*ftValue;
    			sumOfRule += Math.pow(ruleValue, 2);
    		}
		}
    	Set<String> ftKeys = features.keySet();
    	for (String key : ftKeys) {
    			sumOfFt += Math.pow(features.get(key), 2);
		}
    	double denominator = Math.sqrt(sumOfRule*sumOfFt);
    	if(denominator == 0){
    		return 1;
    	}
    	return sumOfPro/denominator;
    }
}
