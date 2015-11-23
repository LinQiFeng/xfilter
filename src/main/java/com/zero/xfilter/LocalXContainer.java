package com.zero.xfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LocalXContainer extends XContainer {
	
	private String XWORD_FILE_PATH;
	
	private Map<String, Object> xWordCache = new HashMap<String, Object>(DEFAULT_XWORD_CAPACITY);
	
	@Override
	public void init() throws Exception {
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			File file = new File(XWORD_FILE_PATH);
			if (file.isFile() && file.exists()) {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				String xWord = null;
				while ((xWord = bufferedReader.readLine()) != null) {
					addXWord(xWord);
				}
			} else {
				throw new Exception("xword file is not exist");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e){}
			}
			if (read != null) {
				try {
					read.close();
				} catch (Exception e){}
			}
		}
	}
	
	/**
	 * 
	 * @description (采用DFA构建字典表)
	 * @author      zero
	 * @createTime  2015年05月02日 下午3:53:00
	 * @param xword
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addXWord(String xword) {
		Map<String, Object> childMap = null;
		Map<String, Object> rootMap = xWordCache;
		for (int i = 0; i < xword.length(); i++) {
			char c = xword.charAt(i);
			Object wordMap = rootMap.get(c);

			if (wordMap != null) {
				rootMap = (Map<String, Object>) wordMap;
			} else {
				childMap = new HashMap<String, Object>();
				childMap.put(XWORD_END_FIELD,  XWORD_NOT_END);
				rootMap.put(c + "", childMap);
				rootMap = childMap;
			}

			if (i == xword.length() - 1) {
				rootMap.put(XWORD_END_FIELD, XWORD_END);
			}
		}
	}

	@Override
	public Map<String, Object> get() {
		return xWordCache;
	}
}
