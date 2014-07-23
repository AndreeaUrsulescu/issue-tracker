package internship.issuetracker.utils;

import org.junit.Test;

public class HTMLtoTEXTTest {


	@Test
	public void testConvert() {
		String t="<script>alert(\"I am an alert box!\");</script>  CCC <a/>";
		assert("&lt;script&gt;alert(&quot;I am an alert box!&quot;);&lt;/script&gt;  CCC &lt;a/&gt;".equals(XSSescape.convert(t)));
	}
	@Test
	public void testRevert() {
		String t="&lt;script&gt;alert(&quot;I am an alert box!&quot;);&lt;/script&gt;  CCC &lt;a/&gt;";
		assert("<script>alert(\"I am an alert box!\");</script>  CCC <a/>".equals(XSSescape.revert(t)));
	}
}
