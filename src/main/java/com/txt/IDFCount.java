package com.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * <p> Copyright 200 by Asiainfo-Linkage Software corporation
 * <p>All rights reserved.
 * <p>版权所有：亚信联创
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>侵权者将受到法律追究。
 * @PURPOSE:  TODO
 * @DESCRIPTION: TODO
 * @AUTHOR: guozb
 * @DATE: 2014-3-20
 * @VERSION PSMA
 * @SINCE 1.0
 * @HISTORY: 2.0
 */
public class IDFCount {
	
	/**
	 * 
	 */
	private static final String COLON = ":";

	/**
	 * 
	 */
	private static final String COMMA = ",";
	final static String keyPath = "D:/tmp/bj/idf/CHI_SVM_F_3000f_1500D.data_temp.txt";
	private File outFile;
	private int docs = 0;
	public IDFCount(){
		init();
	}
	public static void main(String[] args) throws IOException {
//		String ctxPath = "D:/tmp/bj/idf/本地类型_本地商机_IPHONE预约.txt";
		IDFCount count= new IDFCount();
//	    readContent(ctxPath);
		String prePath = "D:/tmp/bj/tousu/含分词_0317_整理_DW_CS_SR_COMPLAIN_DM_201401/服务类/客户投诉/";
//		String outPath = prePath+"/国际/TF_IDF_sum.txt";
		String[] inPaths = {prePath+"国际",prePath+"信息安全",prePath+"网络通信/互联网类业务"
				,prePath+"网络通信/话音基本业务",prePath+"基础业务/费用质疑",prePath+"基础业务/大市场营销"
				,prePath+"基础业务/业务规定、流程不满"};
//		count.setOutFile(new File(outPath));
		String outFileName="TF_IDF_allwords.txt";
		for (String path : inPaths) {
			count.readPathCollectResult(new File(path),new File(path+File.separator+outFileName),false);
		}
//		count.readPathCollectResult(new File(prePath+"网络通信/互联网类业务"),new File(prePath+"网络通信/互联网类业务"+File.separator+outFileName));
        System.out.println("完成任务了！");
//		deleteFile("D:/tmp/bj/tousu/含分词_0317_整理_DW_CS_SR_COMPLAIN_DM_201401/服务类","IDF.txt");
		/* String filePath = "D:/tmp/bj/tousu/含分词_0318_整理_DW_CS_SR_COMPLAIN_DM_201401";
	    ExecutorService exec = Executors.newFixedThreadPool(7);
	    File path = new File(filePath);
	    File[] files = path.listFiles();
	    for (File file : files) {
	    	if(file.getName().contains("dup")){
	    		exec.execute(new IDFThread(file));
	    	}
			
		}
	    exec.shutdown();*/
	}
	
	/**
	 * @param file
	 */
	public void setOutFile(File file) {
		this.outFile = file;
	}

	public static void deleteFile(String path,final String fileName){
		File file = new File(path);
		if(file.isFile() && file.getName().equals(fileName)){
			file.delete();
			System.out.println("删除文件"+file.getPath());
		}
		else if(file.isDirectory()){
			File[] subFiles = file.listFiles();
			for (File file2 : subFiles) {
				deleteFile(file2.getPath(), fileName);
			}
		}
	}
	/**
	 * @param ctxPath
	 * @throws IOException
	 */
	private  void readContent(File ctxPath,File outPath,boolean useKeyWords) throws IOException {
		/*if(!ctxPath.getName().contains("content.txt")){
			ctxPath.delete();
			System.out.println("删除"+ctxPath.getPath());
			return;
		}*/
//		readIDF(ctxPath,outPath);
		System.out.println(Thread.currentThread().getName()+" "+ctxPath.getPath()+"处理中...");
		docs += readFile(ctxPath,useKeyWords);
//	    writeTF_IDF2File(ctxPath, outPath);
		/*df.clear();
		idf.clear();*/
	}
	/**
	 * @param ctxPath
	 * @param outPath
	 * @throws IOException
	 */
	private void writeTF_IDF2File(File ctxPath, File outPath,boolean useKeyWords)
			throws IOException {
		StringBuilder sb = new StringBuilder();
	   /* Integer dfNum = 0;
	    Float dw = 0f;
	    double idfValue = 0;*/
	    if(useKeyWords){
	    	for (String key : keyWords) {
	 		   writeMapInfo(outPath, sb, key);
	 		}
	    }
	    else{
	    	Set<String> keys = df.keySet();
	    	for (String key : keys) {
	    		writeMapInfo(outPath, sb, key);
			}
	    }
		
		df.clear();
		idf.clear();
		System.out.println(Thread.currentThread().getName()+" "+ctxPath.getName()+"输出到"+outPath.getPath());
	}
	/**
	 * @param outPath
	 * @param sb
	 * @param key
	 * @throws IOException
	 */
	private void writeMapInfo(File outPath, StringBuilder sb, String key)
			throws IOException {
		Integer dfNum;
		Float dw;
		double idfValue;
		dfNum = df.get(key);
		   dw = idf.get(key);
		   if(null!=dfNum && dfNum>0 && null!=dw && dw.floatValue()>0){
			       sb.append(key).append(TAB);
			       sb.append(dfNum).append(TAB);
				   BigDecimal bd = new BigDecimal(docs/dw);
				   bd = bd.setScale(6, BigDecimal.ROUND_HALF_UP);
				   idfValue = Math.log(bd.floatValue());
				   sb.append(idfValue).append(TAB);
				   sb.append(idfValue*dfNum);
				   FileUtils.writeLines(outPath,Arrays.asList(sb.toString()),true);
				   sb.delete(0, sb.length());
			 /*  else{
				   sb.append("null");
			   }*/
			 
		   }
	}
	/**
	 * @param ctxPath
	 * @throws IOException 
	 */
	private void readIDF(File ctxPath,File outPath) throws IOException {
		LineIterator li = FileUtils.lineIterator(ctxPath);
		String line = "";
		String[] strs;
		Float ti;
		StringBuilder bi = new StringBuilder();
		while(li.hasNext()){
			line = li.nextLine();
			strs = line.split(TAB);
			if(!"null".equals(strs[2])){
				ti = Integer.parseInt(strs[1])*Float.parseFloat(strs[2]);
				bi.append(line).append(TAB).append(ti);
				FileUtils.writeLines(outPath, Arrays.asList(bi.toString()), true);
				bi.delete(0, bi.length());
			}
		}
		ctxPath.delete();
		System.out.println("删除文件"+ctxPath.getPath());
	}
	public void readPathCollectResult(File inPath,File resultFile,boolean useKeyWords) throws IOException{
        setOutFile(resultFile);
		readPath(inPath,useKeyWords);
		writeTF_IDF2File(inPath, outFile,useKeyWords);
	}
	public  void readPath(File path,boolean useKeyWords) throws FileNotFoundException, IOException {
			if(path.isFile() && path.getName().equals("content.txt")){
//				  String outFile = path.getPath().replaceAll("IDF.txt", "TF_IDF.txt");
				  readContent(path,outFile, useKeyWords);
			}
			else if(path.isDirectory()){
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				readPath(files[i],useKeyWords);
			  }
			}
	}
	/**
	 * 
	 */
	private static final String TAB = "\t";
	/**
	 * 返回文件的投诉文本行数
	 * @param pathName
	 * @return
	 * @throws IOException
	 */
	private  int readFile(File path,boolean useKeyWords) throws IOException{
	   int count=0;
	   LineIterator li = FileUtils.lineIterator(path);
		String line = "";
		String[] strs;
		while(li.hasNext()){
			line = li.nextLine();
//			if(!StringUtils.isEmpty(line)){
			if(count%2!=0){
				strs = line.split(COMMA);
				//记入df
				for (String str : strs) {
                     String[] ws = str.split(COLON);
                     if(useKeyWords){
                    	 if(keyWords.contains(ws[0])){
                    	   addTFMap(ws[0]);
                    	 }
                     }
                     else{
                    	 addTFMap(ws[0]);
                     }
				}
				//记入idf
				if(useKeyWords){
					addLineIDFMap(line);
				}
				else{
					addSetsIDFMap(arraysToSet(strs));
				}
			}
			count++;
//			}
		}
		return (count+1)/2;
	}
	/**
	 * @param strs
	 * @return
	 */
	private Set<String> arraysToSet(String[] strs) {
		if(null!=strs){
			Set<String> set = new HashSet<String>();
			CollectionUtils.addAll(set, strs);
			return set;
		}
		else{
			return new HashSet<String>();
		}
	}
	public void addSetsIDFMap(Set<String> words){
		String[] strs;
		String key;
		for (String word : words) {
			strs = word.split(COLON);
			key = strs[0];
			if(idf.containsKey(key)){
				idf.put(key, idf.get(key)+1);
			}
			else{
				idf.put(key, 0f);
			}
		}
	}
	public  void addTFMap(String word){
		if(df.containsKey(word)){
			df.put(word, df.get(word)+1);
		}
		else{
			df.put(word, 0);
		}
	}
	/**
	 * 分词结果
	 * @param lineWords
	 */
	public  void addLineIDFMap(String lineWords){
			for (String word : keyWords) {
				if(lineWords.contains(COMMA+word+COLON)){
					if(idf.containsKey(word)){
						idf.put(word, idf.get(word)+1);
					}
					else{
						idf.put(word, 0f);
					}
				}
			}
	}
	private static void initKeyWords(String pathName) throws IOException{
		keyWords = new ArrayList<String>();
		LineIterator li = FileUtils.lineIterator(new File(pathName));
		String line = "";
		String[] strs;
		while(li.hasNext()){
			line = li.nextLine();
			strs = line.split(TAB);
			keyWords.add(strs[1]);
		}
	}
	public  void init(){
		df = new HashMap<String, Integer>();
		idf = new HashMap<String, Float>();
	}
   private static  List<String> keyWords = null;
   static{
	   try {
		initKeyWords(keyPath);
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
   private  Map<String,Integer> df = null;
   
   private  Map<String,Float> idf = null;
}
class IDFThread extends Thread{
	private File path = null;
	public IDFThread(File path) {
      this.path = path;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		IDFCount counter = new IDFCount();
		try {
			counter.readPath(path,true);
			System.out.println(Thread.currentThread().getName()+"完成任务了==》"+path.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}