package internship.issuetracker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;



public class ApplicationParameters {
	
	public static int itemsPerPage=2;
	public static String activationEmailMessagePart1="To activate your account please click the link below\n\n";
	public static String activationEmailMessagepart2="\n\n Thank you for your interest.";
	public static String applicationRoot="http://localhost:8080";
	public static String changedIssueEmail="\n\nFor the issue :\n\nhttp://localhost:8080/issue-tracker/issues/issue/";
	public static String stateHasBeenChangedMessage="State has been changed\n";
	public static String emailAdress="Graduates@endava.com";
	public static String activationEmailSubject="Activation-issueTracker";
	public static Properties properties;
	
//	@Autowired
//	private static ApplicationContext ac;
//	
//	static{
//		loadPropertiesFile();
//		itemsPerPage=Integer.parseInt(properties.getProperty("itemsPerPage"));
//		activationEmailMessagePart1=properties.getProperty("activationEmailContentPart1");
//		activationEmailMessagepart2=properties.getProperty("activationEmailContentPart2");
//		applicationRoot=properties.getProperty("applicationRoot");
//		changedIssueEmail=properties.getProperty("changedIssueEmail");
//		stateHasBeenChangedMessage=properties.getProperty("stateHasBeenChangedMessage");
//		emailAdress=properties.getProperty("emailAdress");
//		activationEmailSubject=properties.getProperty("activationEmailSubject");
//	}
//	
//	public static void loadPropertiesFile()
//	{
//		String locationOfThEPropertiesFile ="applicationParameters.properties";
//		properties=new Properties();
//		InputStream input = (InputStream) ac.getResource("applicationParameters.properties");
//		try {
//			properties.load(input);
//			} catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}
}
