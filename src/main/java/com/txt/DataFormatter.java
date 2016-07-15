package com.txt;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

public class DataFormatter {
    /**
     * 列分隔符
     */
	private static final String COL_SEP = "\t";
public static void main(String[] args) throws IOException {
	String str = "+$";
	formatSnData();
	System.out.println(str.replace("$", "1"));
}
   public static void formatSnData() throws IOException{
	   String string="广义投诉~网络@20140416.txt";//"addressSuffix.dic";//"20140411.txt";
	File file = new File("D:/tmp/sn/" + string);
		LineIterator li = org.apache.commons.io.FileUtils
				.lineIterator(file);
		File nFile = new File("D:/tmp/sn/整理_" + string);
		String tmp = null;
		while (li.hasNext()) {
			tmp=li.nextLine();
			if(!StringUtils.isEmpty(tmp) && !StringUtils.isBlank(tmp)){
				int index = tmp.indexOf(COL_SEP);
				org.apache.commons.io.FileUtils.writeLines(nFile,
						Arrays.asList(tmp.substring(0, index)+COL_SEP+tmp.substring(index+1).replaceAll(" ", "")), true);
			}
			
		}
   }
	/**
	 * 北京移动数据清洗
	 * 
	 * @throws IOException
	 */
	public static void formatData() throws IOException {
		String[] fileNames = {/* "DW_CS_SR_COMPLAIN_DM_201312.csv" */"DW_CS_SR_COMPLAIN_DM_201401.csv" };
		for (String string : fileNames) {
			File file = new File("D:/tmp/bj/" + string);
			LineIterator li = org.apache.commons.io.FileUtils
					.lineIterator(file);
			File nFile = new File("D:/tmp/bj/整理_" + string);
			String tmp = null;
			String day = null;
			String type = "";
			while (li.hasNext()) {
				String[] str = li.nextLine().split(",");
				String ctx = str[28];
				day = str[1];
				type = str[4];
				/* if(type.contains("4G") || type.contains("4g")){ */
				System.out.println(type.replaceAll("→", "/") + ":" + ctx);
				tmp = type.replaceAll("→", "/") + COL_SEP
						+ ctx.replaceAll("@", "<br>") + COL_SEP
						+ formateDate(day);
				org.apache.commons.io.FileUtils.writeLines(nFile,
						Arrays.asList(tmp.replaceAll("\"", "")), true);
				// }
			}
		}
	}
    /**
     * 将20121212转为2012-12-12
     * @param str
     * @return
     */
	public static String formateDate(String str) {
		if (StringUtils.isNotEmpty(str) && str.length() >= 8) {
			return str.substring(0, 4) + '-' + str.substring(4, 6) + '-'
					+ str.substring(6);
		}
		return StringUtils.EMPTY;
	}

}
