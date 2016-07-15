package com.thread;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class LocaleUtil {

	private static Logger log = Logger.getLogger(LocaleUtil.class);
	
	private static final ThreadLocal<Locale> currentLoacal = new ThreadLocal<Locale>();
	
	public static ThreadLocal<Locale> getCurrentloacal() {
		return currentLoacal;
	}

	private static Map<String,String[]> sysLocaleInfoMap = new HashMap<String,String[]>();
	
	/**
	 * 
	 * 按指定的组件名称（或系统、模块名称）加载资源文件
	 * 
	 * @param sysCodeSet 系统、模块、组件名称，为空会抛空指针异常
	 * @param filePathSet 资源文件的目录，如config/aibi_favor/，最后要带目录分隔符/
	 * @param fileNameSet 资源文件的名称，除去语言、国家、文件类型等信息，
	 * 		如favor-appresources_zh_CN.properties对应的名称为“favor-appresources”
	 * @param fileTypeSet	资源文件类型，默认都是".properties"
	 * @param defaultLanguageSet 资源文件类型，默认都是"en"
	 * void
	 *
	 */
	public static void addSysLocale(String sysCodeSet, String filePathSet, String fileNameSet,
			String fileTypeSet, String defaultLanguageSet) {
		if(sysCodeSet==null || sysCodeSet.trim().length()<1) {
			log.error("----SysCode can't be null!");
			throw new NullPointerException("----SysCode can't be null!");
		}
		nullValueWarn(filePathSet,"i18n filePath");
		nullValueWarn(fileNameSet,"i18n fileName");
		if(defaultLanguageSet==null || defaultLanguageSet.trim().length()<1) {
			defaultLanguageSet = "en";
		}
		if(fileTypeSet==null || fileTypeSet.trim().length()<1) {
			fileTypeSet = ".properties";
		}
		String[] localeInfo = new String[4];
		localeInfo[0] = filePathSet;
		localeInfo[1] = fileNameSet;
		localeInfo[2] = fileTypeSet;
		localeInfo[3] = defaultLanguageSet;
		//toUpperCase
		sysLocaleInfoMap.put(sysCodeSet.trim().toUpperCase(), localeInfo);
	}

	private static void nullValueWarn(String inputStr,String inputDesc) {
		if(inputStr==null || inputStr.trim().length()<1) {
			log.error(inputDesc + " is null!");
		}
	}
	
	
	/**
	 * 
	 * 通过组件名、key值获取资源信息。
	 * 
	 * @param sysCode 组件名(系统、模块名)
	 * @param key		资源文件中的key值
	 * @return
	 * String
	 *
	 */
	public static String getLocaleMessage(String sysCode, String key) {
		ResourceBundle bundle = null;
		if(sysCode==null) {
			log.error("sysCode can't be null!");
			return null;
		}
		String[] localeInfo = sysLocaleInfoMap.get(sysCode.trim().toUpperCase());
		if(currentLoacal.get()==null) {
			currentLoacal.set(new Locale(localeInfo[3]));
		}
		Locale value = currentLoacal.get();
		try {
			bundle = ResourceBundle.getBundle(localeInfo[0] + localeInfo[1], value);
		} catch (MissingResourceException e) {
			bundle = ResourceBundle.getBundle(localeInfo[0] + localeInfo[1], Locale.ENGLISH);
		}
		String result = "";
		try {
			result = bundle.getString(key);
		}catch(Exception e) {
			log.error("--Country--" + value.getCountry() + "-----Language:" 
					+ value.getLanguage() + "---fileName---" + localeInfo[0] + localeInfo[1]+", key["+key+"] not found!");
			log.error(e.getMessage());
		}
		
		return result;
	}
	/**
	 * 
	 * 通过组件名、key值获取资源信息。
	 * 
	 * @param sysCode 组件名(系统、模块名)
	 * @param key		资源文件中的key值
	 * @param messageArgs  资源文件中的变量组
	 * @return
	 * String
	 *
	 */
	public static String getLocaleMessage(String sysCode, String key,Object[] messageArgs) {
		String result = getLocaleMessage(sysCode,key);
		if(key !=null&&messageArgs!=null)
			result = MessageFormat.format(result, (Object[]) messageArgs);
		return result;
	}
	/**
	 * 
	 * 通过组件名、key值获取资源信息。
	 * 
	 * @param sysCode 组件名(系统、模块名)
	 * @param key		资源文件中的key值
	 * @param messageArg  资源文件中的变量
	 * @return
	 * String
	 *
	 */
	public static String getLocaleMessage(String sysCode, String key,String messageArg) {
		String result = getLocaleMessage(sysCode,key);
		if(key !=null&&messageArg!=null)
			result = MessageFormat.format(result, messageArg);
		return result;
	}
	

	public static Locale getLocale() {
		return currentLoacal.get();
	}

	/**
	 * 
	 * 设置Locale信息
	 * @param locale
	 * void
	 *
	 */
	public static void setLocale(Locale locale) {
		currentLoacal.set(locale);
	}

}
