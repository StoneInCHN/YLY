
package com.yly.beans;

/**
 * 公共参数
 * 
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" , "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** common-config.xml文件路径 */
	public static final String COMMON_CONFIG_XML_PATH = "/common-config.xml";

	/** common-config.properties文件路径 */
	public static final String COMMON_CONFIG_PROPERTIES_PATH = "/common-config.properties";
	
	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}