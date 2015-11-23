package com.zero.xfilter;

public class XFilterTest {

	public static void main(String[] args) {
		XContainer xContainer = new LocalXContainer();
		xContainer.addXWord("you");
		xContainer.addXWord("bb");
		
		XFilter filter = new DefaultXFilter(xContainer);
		String result = filter.replaceX("You can you up, not can not bb", "*");
		System.out.println(result);
	}
}
