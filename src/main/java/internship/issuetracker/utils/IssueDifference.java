package internship.issuetracker.utils;

import internship.issuetracker.entities.Issue;



public class IssueDifference {
	public static String generateDifference(Issue newIssue, Issue oldIssue) {
		String title = getStringDifferences(newIssue.getTitle(), oldIssue.getTitle());
		String content = getStringDifferences(newIssue.getContent(), oldIssue.getContent());

    String msg = ApplicationParameters.changedIssueEmail + newIssue.getId();
	StringBuilder result = new StringBuilder();

	if (title != null){
			result.append("Title" + title + " from \"" + oldIssue.getTitle() + "\" to \"" + newIssue.getTitle() + "\" .\n");
	}

	if (content != null){
	    result.append("Content" + content + ".\n");
	}
	if (newIssue.getState() != oldIssue.getState()){
	    result.append(ApplicationParameters.stateHasBeenChangedMessage+ issue1.getState() + " to " +  issue2.getState() + ".\n");
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
