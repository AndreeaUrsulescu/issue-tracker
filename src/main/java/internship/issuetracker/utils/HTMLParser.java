package internship.issuetracker.utils;

public class HTMLParser {

	
	public static String convert(String fullText)
	{
		StringBuilder parsedText=new StringBuilder();
		boolean inTag=false;
		int counter=0;
		char c;
		for (int i = 0; i < fullText.length(); i++) {
			c=fullText.charAt(i);

			if(c=='<')
			{
				inTag=true;
				continue;
			}else if(c=='>')
			{
				inTag=false;	
				continue;
			}
			
			if(!inTag)
			{
				counter++;
				if(counter<147)
				{
					parsedText.append(c);
				}
			}
		
		}
		
		String result=parsedText.toString();
		result=result.replaceAll("&lt;", "<");
		result=result.replaceAll("&gt;", ">");
		
		if(counter>=147)
			result=result+"...";
		
		return result;
	}
}