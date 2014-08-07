package internship.issuetracker.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class HTMLParserTest {

    @Test
    public void testConvert() {
        String rezult = HTMLParser.convert("<p>content</p>");
        String expResult = "content";
        assertTrue(rezult.equals(expResult));
    }

    @Test
    public void testConvertForContentSearch() {
        String rezult = HTMLParser.convertForContentSearch("<p>content</p>");
        String expResult = "content";
        assertTrue(rezult.equals(expResult));
    }

}
