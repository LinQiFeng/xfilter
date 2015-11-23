package com.zero.xfilter;

import java.util.Set;

public interface XFilter {

	/**
	 * 
	 * @description (是否包含XConainer指定的词)
	 * @author      zero
	 * @createTime  2015年05月02日 下午5:12:44
	 * @param content
	 * @return
	 */
	public boolean hasXWord(String content);
	
	/**
	 * 
	 * @description (获取指定字符串中包含{@link XContainer}中包含的词)
	 * @author      zero
	 * @createTime  2015年05月02日 下午5:13:04
	 * @param content
	 * @return
	 */
	public Set<String> getXWord(String content);
	
	/**
	 * 
	 * @description (将指定字符串中包含了{@link XContainer}中的词替换成replacement)
	 * @author      zero
	 * @createTime  2015年05月02日 下午5:15:28
	 * @param content
	 * @param replacement
	 * @return
	 */
	public String replaceX(String content, String replacement);
}
