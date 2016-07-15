package com.lucene;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.junit.Test;


/**
 * <p> Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>All rights reserved.
 * <p>版权所有：亚信联创
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>侵权者将受到法律追究。
 * @PURPOSE:  TODO
 * @DESCRIPTION: TODO
 * @AUTHOR: guozb
 * @DATE: 2014-6-13
 * @VERSION PSMA
 * @SINCE 1.0
 * @HISTORY: 2.0
 */
public class TestIndexer {
	
    public static final	String indexPath = "./testIndex";//"./productIndex";
	@Test
	public void testIndex() throws CorruptIndexException, LockObtainFailedException, IOException{
		//standard
	/*	Analyzer sd = new StandardAnalyzer(Version.LUCENE_30);
		index(sd);*/
		//ik
		/*Analyzer ik = new IKAnalyzer();
		index(ik);*/
//		index(ik);
//		indexAdd(ik);
		System.out.println("索引ok");
	}

	/**
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	private void index(Analyzer analyzer) throws CorruptIndexException,
			LockObtainFailedException, IOException {
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_30, analyzer);
		IndexWriter writer = new IndexWriter(FSDirectory.open(new File(indexPath)), config);
		String[][] rules = {{"梦网:5","短信:3"},{"梦网:5","彩信:3"},{"国际:5","短信:3"}};
		String[] pros = {"梦网短信","梦网彩信","国际短信"};
		int len = rules.length;
		String[] ruleScore = null;
		String proName = "";
		Document doc;
		for(int i=0; i<len; i++){
			 ruleScore = rules[i];
			 proName = pros[i];
			 doc = new Document();
			 doc.add(new Field("product",proName,Field.Store.YES,Field.Index.ANALYZED,TermVector.WITH_POSITIONS_OFFSETS));
			 for (int j = 0; j < ruleScore.length; j++) {
				 String[] rs = StringUtils.splitByWholeSeparator(ruleScore[j], ":");
					Field field = new Field("rule"+j,rs[0],Field.Store.YES,Field.Index.ANALYZED);
					 field.setBoost(Float.valueOf(rs[1]));
					 doc.add(field); 
			}
			 writer.addDocument(doc);
		}
		 writer.optimize();
		 writer.close();
	}
	
	public void indexAdd(final Analyzer analyzer) throws IOException{
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_30, analyzer);
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexWriter writer = new IndexWriter(directory, config);
		String[][] rules = {{"信息:5","短信:3","谣言:5"},{"催缴:5","话费:3","短信:3","提醒:5"},{"动感地带:5","短信:3"}};
		String[] pros = {"收到信息内容涉及到短信谣言","催缴及话费短信提醒","动感地带短信套餐"};
		int len = rules.length;
		String[] ruleScore = null;
		String proName = "";
		Document doc;
		for(int i=0; i<len; i++){
			 ruleScore = rules[i];
			 proName = pros[i];
			 doc = new Document();
			 doc.add(new Field("product",proName,Field.Store.YES,Field.Index.NOT_ANALYZED));
			 for (int j = 0; j < ruleScore.length; j++) {
				 String[] rs = StringUtils.splitByWholeSeparator(ruleScore[j], ":");
					Field field = new Field("rule"+j,rs[0],Field.Store.YES,Field.Index.ANALYZED);
					 field.setBoost(Float.valueOf(rs[1]));
					 doc.add(field); 
			}
			 writer.addDocument(doc);
		}
		 writer.optimize();
		 writer.close();
	}
	
	@Test
	public void indexFile() throws IOException{
		/*Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_30, analyzer );
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexWriter writer = new IndexWriter(directory, config);
		String filePath = "D:\\tmp\\pro_rule.txt";
		LineIterator li = FileUtils.lineIterator(new File(filePath));
		String[] lineCon = null;
		String productName = "";
		String rule = "";
		String[] rules = null;
		Document doc = null;
		while(li.hasNext()){
			lineCon = StringUtils.splitByWholeSeparator(li.nextLine(), "\t");
			productName = lineCon[0];
			rules = StringUtils.splitByWholeSeparator(lineCon[1], "、");
			int len = rules.length;
			 doc = new Document();
			 doc.add(new Field("product",productName,Field.Store.YES,Field.Index.ANALYZED));
			 int boost = 0;
//			 doc.add(new Field("rule0",lineCon[1],Field.Store.YES,Field.Index.ANALYZED));
			 for(int j=0; j<len; j++){
				 rule = rules[j];
				 Field field = new Field("rule"+j,rule,Field.Store.YES,Field.Index.ANALYZED);
				 boost = 5 - j;
				 boost = boost>0?boost:1;
				 field.setBoost(boost);
				 System.out.println(rule+" boost:"+boost);
				 doc.add(field); 
			}
			 writer.addDocument(doc);
		}		
		 writer.optimize();
		 writer.close();*/
	}
	public Map<String,Integer> transToMap(final String[] ruleScore){
		Map<String, Integer> rl = new HashMap<String, Integer>();
		for(String rule:ruleScore){
			String[] rs = StringUtils.splitByWholeSeparator(rule, ":");
			rl.put(rs[0], Integer.valueOf(rs[1]));
		}
		return rl;
	}

}
