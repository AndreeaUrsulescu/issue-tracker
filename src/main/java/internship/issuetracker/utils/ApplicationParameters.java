package internship.issuetracker.utils;

import internship.issuetracker.service.LabelService;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public final class ApplicationParameters {
	
    private ApplicationParameters(){
        
    }
    
	private static final Logger LOG = Logger.getLogger(LabelService.class.getName());
	
	public static final int itemsPerPage;
	public static final String activationEmailMessagePart1;
	public static final String activationEmailMessagepart2;
	public static final String applicationRoot;
	public static final String changedIssueEmail;
	public static final String stateHasBeenChangedMessage;
	public static final String emailAdress;
	public static final String activationEmailSubject;
	public static Properties properties;
	public static final String contextPath;
	
	static{
	    int tempitemsPerPage;
		loadPropertiesFile();
		try{
		    tempitemsPerPage=Integer.parseInt(properties.getProperty("itemsPerPage"));
		} catch (NumberFormatException numberFormatException){
		    LOG.log(Level.INFO, "Could not parse number of items per page, using default 10",numberFormatException);
		    tempitemsPerPage=6;
		}
		itemsPerPage = tempitemsPerPage;
		activationEmailMessagePart1=properties.getProperty("activationEmailContentPart1");
		activationEmailMessagepart2=properties.getProperty("activationEmailContentPart2");
		applicationRoot=properties.getProperty("applicationRoot");
		contextPath=properties.getProperty("contextPath");
		changedIssueEmail=properties.getProperty("changedIssueEmailP")+applicationRoot+contextPath+properties.getProperty("changedIssueEmailS");
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
			LOG.log(Level.INFO, "Error while loading the properties file!",e1);
        }

    }
}
