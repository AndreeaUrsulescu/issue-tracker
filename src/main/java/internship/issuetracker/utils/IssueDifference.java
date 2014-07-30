package internship.issuetracker.utils;

import internship.issuetracker.entities.Issue;



public class IssueDifference {
    public static String generateDifference(Issue issue1, Issue issue2){
    String msg="\n\nFor the issue :\n\n" +"http://localhost:8080/issue-tracker/issues/issue/"+issue1.getId();
    
	StringBuilder result = new StringBuilder();
	String title = getStringDifferences(issue1.getTitle(), issue2.getTitle());
	if (title != null){
	    result.append("Title" + title + " from "+ issue1.getTitle()+ " to " + issue2.getTitle() + " .\n");
	}
	String content = getStringDifferences(issue1.getContent(), issue2.getContent());
	if (content != null){
	    result.append("Content" + content + ".\n");
	}
	if (issue1.getState() != issue2.getState()){
	    result.append("State has been changed from " + issue1.getState() + " to " +  issue2.getState() + "\n");
	}
	
	return result.append(msg).toString();
    }
    
    private static String getStringDifferences(String original, String modified){
	if(!original.equals(modified)){
	    return " has been modified";
	}
	return null;
    }
    
   
    
}
