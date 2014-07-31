package internship.issuetracker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



public class ApplicationParameters {
	
	public static int itemsPerPage;
	public static String activationEmailMessagePart1;
	public static String activationEmailMessagepart2;
	public static String applicationRoot;
	public static String changedIssueEmail;
	public static String stateHasBeenChangedMessage;
	public static String emailAdress;
	public static String activationEmailSubject;
	public static Properties properties;
	
	static{
		loadPropertiesFile();
		itemsPerPage=Integer.parseInt(properties.getProperty("itemsPerPage"));
		activationEmailMessagePart1=properties.getProperty("activationEmailContentPart1");
		activationEmailMessagepart2=properties.getProperty("activationEmailContentPart2");
		applicationRoot=properties.getProperty("applicationRoot");
		changedIssueEmail=properties.getProperty("changedIssueEmail");
		stateHasBeenChangedMessage=properties.getProperty("stateHasBeenChangedMessage");
		emailAdress=properties.getProperty("emailAdress");
		activationEmailSubject=properties.getProperty("activationEmailSubject");
	}
	
	public static void loadPropertiesFile()
	{
		String locationOfThEPropertiesFile ="applicationParameters.properties";
		properties=new Properties();
		InputStream input = ApplicationParameters.class.getClassLoader().getResourceAsStream(locationOfThEPropertiesFile);
		try {
			properties.load(input);
			} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
