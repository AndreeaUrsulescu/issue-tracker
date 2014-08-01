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
				parsedText.append(c);
				inTag=true;
				continue;
			}else if(c=='>')
			{
				parsedText.append(c);
				inTag=false;	
				continue;
			}
			
			if(!inTag)
			{
				counter++;
				if(counter<150)
				{
					parsedText.append(c);
				}
			}
			else
			{
				parsedText.append(c);
			}
			
			
			
			
			
		}
		
		
		return parsedText.toString();
	}
}
