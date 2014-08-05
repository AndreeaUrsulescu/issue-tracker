package internship.issuetracker.utils;

import internship.issuetracker.service.LabelService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class ApplicationParameters {
	
	private static final Logger log = Logger.getLogger(LabelService.class.getName());
	
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
		Resource resource = null;
		InputStream inputStream   = null;
		resource = new ClassPathResource("applicationParameters.properties");
		   try {
			inputStream = resource.getInputStream();
			properties=new Properties();
			properties.load(inputStream);
		} catch (Exception e1) {
			log.log(Level.INFO, "Error while loading the properties file!");
			e1.printStackTrace();
		}
	
	}
}
