package internship.issuetracker.utils;

import org.apache.commons.lang3.StringEscapeUtils;

public class XSSescape {

	public static String convert(String msg) {
		String x=StringEscapeUtils.escapeHtml4(msg);
		String y=StringEscapeUtils.escapeJava(x);
		return y;
	}
	
	public static String revert(String msg) {
		String x=StringEscapeUtils.unescapeHtml4(msg);
		String y=StringEscapeUtils.unescapeJava(x);
		return y;
	}
	
	

}
