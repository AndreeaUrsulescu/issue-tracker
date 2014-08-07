package internship.issuetracker.utils;

import internship.issuetracker.service.LabelService;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public final class ApplicationParameters {

    private static final Logger LOG = Logger.getLogger(LabelService.class.getName());
    public static final int ITEMS_PER_PAGE;
    public static final String ACTIVATION_EMAIL_PART_1;
    public static final String ACTIVATION_EMAIL_PART_2;
    public static final String APPLICATION_ROOT;
    public static final String CHANGED_ISSUE_EMAIL;
    public static final String STATE_CHANGED_MSG;
    public static final String EMAIL_ADDRESS;
    public static final String ACTIVATION_EMAIL_SUBJECT;
    private static Properties properties;
    public static final String CONTEXT_PATH;
    
    private ApplicationParameters() {

    }



    static {
        loadPropertiesFile();
        ITEMS_PER_PAGE = setNumberofIssuesPerPage();
        ACTIVATION_EMAIL_PART_1 = properties.getProperty("activationEmailContentPart1");
        ACTIVATION_EMAIL_PART_2 = properties.getProperty("activationEmailContentPart2");
        APPLICATION_ROOT = properties.getProperty("applicationRoot");
        CONTEXT_PATH = properties.getProperty("contextPath");
        CHANGED_ISSUE_EMAIL = properties.getProperty("changedIssueEmailP") + APPLICATION_ROOT + CONTEXT_PATH + properties.getProperty("changedIssueEmailS");
        STATE_CHANGED_MSG = properties.getProperty("stateHasBeenChangedMessage");
        EMAIL_ADDRESS = properties.getProperty("emailAdress");
        ACTIVATION_EMAIL_SUBJECT = properties.getProperty("activationEmailSubject");

    }

    private static int setNumberofIssuesPerPage() {
        int tempitemsPerPage;
        try {
            tempitemsPerPage = Integer.parseInt(properties.getProperty("itemsPerPage"));
        } catch (NumberFormatException numberFormatException) {
            LOG.log(Level.INFO, "Could not parse number of items per page, using default 10", numberFormatException);
            tempitemsPerPage = 6;
        }
        return tempitemsPerPage;
    }

    public static void loadPropertiesFile() {
        Resource resource = null;
        InputStream inputStream = null;
        resource = new ClassPathResource("applicationParameters.properties");
        try {
            inputStream = resource.getInputStream();
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e1) {
            LOG.log(Level.INFO, "Error while loading the properties file!", e1);
        }

    }
}
