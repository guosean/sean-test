package com.lucene;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>
 * All rights reserved.
 * <p>
 * 版权所有：亚信联创
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>
 * 侵权者将受到法律追究。
 * 
 * @PURPOSE: TODO
 * @DESCRIPTION: TODO
 * @AUTHOR: guozb
 * @DATE: 2014-5-29
 * @VERSION PSMA
 * @SINCE 1.0
 * @HISTORY: 2.0
 */
public class TestSearcher {
	Directory dir;
	IndexSearcher searcher;
	@Before
	public void setUp() throws CorruptIndexException, IOException{
		 dir = FSDirectory.open(new File(TestIndexer.indexPath));
		// 打开索引
		 IndexReader ir = IndexReader.open(dir);
		 ExecutorService es = Executors.newFixedThreadPool(10);
		 searcher = new IndexSearcher(ir,es);
		 
	}
	
	@Test
	public void testSearchByTerm() throws CorruptIndexException, IOException,
			ParseException {
		String q = "短信";
		Directory dir = FSDirectory.open(new File(TestIndexer.indexPath));
		// 打开索引
		IndexSearcher is = new IndexSearcher(dir);
		IndexReader reader = is.getIndexReader();
		TermQuery tq = new TermQuery(new Term("rule1", q));
		TopDocs tps = is.search(tq, 10,new Sort(new SortField("product", SortField.STRING)));
		ScoreDoc[] sds = tps.scoreDocs;
		for (ScoreDoc scoreDoc : sds) {
			Document doc = is.doc(scoreDoc.doc);
			TermFreqVector vec =  reader.getTermFreqVector(scoreDoc.doc, "product");
			System.out.println("vector:"+vec);
			// 返回匹配文件名
			System.out.println(doc.get("product") + " ----- " + doc.get("rule0"));
		}
		is.close();
	}
	@Test
	public void testPhraseQuery() throws IOException{
		PhraseQuery pq = new PhraseQuery();
		pq.add(new Term("product", "一起"));
		pq.add(new Term("product", "回家"));
		pq.setSlop(10);
		TopDocs tps = searcher.search(pq, 10);
		// Analyzer az = new StandardAnalyzer(Version.LUCENE_30);
		ScoreDoc[] sds = tps.scoreDocs;
		for (ScoreDoc scoreDoc : sds) {
			Document doc = searcher.doc(scoreDoc.doc);
			// 返回匹配文件名
			System.out.println(doc.get("product") + " ----- " + doc.get("rule"));
		}
		searcher.close();
	}
	
	@Test
	public void testByWildcardQuery() throws IOException{
		String q = "梦网*";
//		TermQuery tq = new TermQuery(new Term("district", q));
		WildcardQuery wq = new WildcardQuery(new Term("rule", q));
		TopDocs tps = searcher.search(wq, 10);
		// Analyzer az = new StandardAnalyzer(Version.LUCENE_30);
		ScoreDoc[] sds = tps.scoreDocs;
		for (ScoreDoc scoreDoc : sds) {
			Document doc = searcher.doc(scoreDoc.doc);
			// 返回匹配文件名
			System.out.println(doc.get("product") + " ----- " + doc.get("rule"));
		}
		searcher.close();
	}
	
	@Test
	public void testQueryParser() throws ParseException, IOException{
	
		QueryParser qp = new QueryParser(Version.LUCENE_30, "rule", new StandardAnalyzer(Version.LUCENE_30));
		Query query = qp.parse("梦网短信  ");
		TopDocs tps = searcher.search(query, 10);
		ScoreDoc[] sds = tps.scoreDocs;
		for (ScoreDoc scoreDoc : sds) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.print(scoreDoc.score);
			// 返回匹配文件名
			System.out.println(doc.get("product") + " ----- " + doc.get("rule"));
		}
		searcher.close();
		
	}
	
	@Test
	public void testMultiQueryParser() throws ParseException, CorruptIndexException, IOException{
		/*String[] fields = {"product","rule0","rule1","rule2","rule3","rule4","rule5"};
		QueryParser qp = new MultiFieldQueryParser(Version.LUCENE_30,fields , new IKAnalyzer()new StandardAnalyzer(Version.LUCENE_30));
		Query query = qp.parse("+专享 +188");
		long start = System.nanoTime();
		TopDocs tps = searcher.search(query, 10);
		System.out.println(" time userd:"+(System.nanoTime()-start));
		ScoreDoc[] sds = tps.scoreDocs;
	   Explanation exp = null;
		for (ScoreDoc scoreDoc : sds) {
			Document doc = searcher.doc(scoreDoc.doc);
			exp = searcher.explain(query, scoreDoc.doc);
			System.out.println(exp);
			System.out.print(scoreDoc.score);
			// 返回匹配文件名
			System.out.println(doc.get("product") + " ----- " + doc.get("rule0")+"---"+doc.get("rule1"));
		}
		searcher.close();*/
	}

}
