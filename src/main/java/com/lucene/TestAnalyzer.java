package com.lucene;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-13
 */
public class TestAnalyzer {
    @Test
	public void testStandardAnalyzer() throws Exception{
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		analyze(analyzer, "梦网短信息");
	}
    @Test
    public void testIKAnalyzer() throws Exception{
    	/*Analyzer analyzer = new IKAnalyzer();
		analyze(analyzer, "梦网短信");
		analyze(analyzer, "“和你一起回家”充值送车票活动");*/
    }
    @Test
    public void testSimpleAnalyzer() throws Exception{
    	Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_30);
		analyze(analyzer, "梦网短信");
		analyze(analyzer, "梦网彩信");
    }
    
    public void analyze(Analyzer analyzer, String text) throws Exception {
        TokenStream tokenStream = analyzer.tokenStream("",new StringReader(text));
        CharTermAttribute termAtt = tokenStream.addAttribute(CharTermAttribute.class); 
    	OffsetAttribute offAtt = tokenStream.addAttribute(OffsetAttribute.class);
        while(tokenStream.incrementToken()){
        	System.out.println(termAtt.toString() + " " + offAtt.startOffset() + " " + offAtt.endOffset());
        }
    }
    @Test
    public void test(){
    	int mid = 8 >>> 1;
    	Assert.assertTrue(4==mid);
        mid = 8 >> 1;
        Assert.assertTrue(4==mid);
    }
	
}
