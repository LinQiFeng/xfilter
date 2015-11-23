package com.zero.xfilter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zero.xfilter.util.Utils;

public class DefaultXFilter implements XFilter {
	
	private XContainer xContainer;
	
	public DefaultXFilter(XContainer XContainer) {
		this.xContainer = XContainer;
	}

	@Override
	public boolean hasXWord(String content) {
		for (int index = 0; index < content.length(); index++) {
			int matchCharCount = getXWordMatchLen(content, index);
			if (matchCharCount > 0) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public int getXWordMatchLen(String content, int beginIndex) {
		boolean endFlag = false;
		int matchCharCount = 0;
		char c = 0;
		Map<String, Object> wordMap = xContainer.get();
		for (int index = beginIndex; index < content.length(); index++) {
			c = content.charAt(index);
			wordMap = (Map<String, Object>)wordMap.get(c + "");
			if (wordMap != null) {
				matchCharCount++;
				if (xContainer.isEnd(wordMap)) {
					endFlag = true;
					break;
				}
			} else {
				break;
			}
		}
		if (matchCharCount < 2 || !endFlag) {
			return 0;
		}
		return matchCharCount;
	}

	@Override
	public Set<String> getXWord(String content) {
		Set<String> xWordSet = new HashSet<String>();
		for (int index = 0; index < content.length(); index++) {
			int matchLength = getXWordMatchLen(content, index);
			if (matchLength > 0) { 
				xWordSet.add(content.substring(index, index + matchLength));
				index = index + matchLength - 1;
			}
		}
		return xWordSet;
	}

	@Override
	public String replaceX(String content, String replacement) {
		String result = content;
		Set<String> set = getXWord(content); // 获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = Utils.concat(replacement, word.length());
			result = result.replaceAll(word, replaceString);
		}

		return result;
	}
}
