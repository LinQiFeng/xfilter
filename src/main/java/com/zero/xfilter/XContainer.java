package com.zero.xfilter;

import java.util.Map;

public abstract class XContainer {
	
	protected static final String XWORD_END_FIELD = "$end$";
	
	protected static final String XWORD_END = "1";
	
	protected static final String XWORD_NOT_END = "0";
	
	protected static int DEFAULT_XWORD_CAPACITY = 64;
	
	public abstract void init() throws Exception;
	
	public abstract Map<String, Object> get();
	
	public boolean isEnd(Map<String, Object> wordMap) {
		if (XWORD_END.equals(wordMap.get(XWORD_END_FIELD))) {
			return true;
		}
		return false;
	}
	
	public abstract void addXWord(String xword);
}
