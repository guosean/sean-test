package com.txt;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-10
 */
public class TxtFilter {
	@Test
	public void filterBlankLine() throws IOException{
		File inFile = new File("D:\\tmp\\product.txt");
		File outFile = new File("D:\\tmp\\product_n.txt");
		LineIterator li = FileUtils.lineIterator(inFile);
		String line = "";
		while(li.hasNext()){
			line = li.nextLine();
			if(StringUtils.isNotBlank(line)){
				FileUtils.writeStringToFile(outFile, line+"\n", true);
			}
		}
	}

}
