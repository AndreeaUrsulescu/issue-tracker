package internship.issuetracker.utils;

import internship.issuetracker.entities.Issue;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;

import difflib.DiffUtils;
import difflib.Patch;


public class IssueDifference {
	
	@Value("${StateHasBeenChangedMessage")
	static private String stateMessage;
		
    public static String generateDifference(Issue issue1, Issue issue2){
	StringBuilder result = new StringBuilder();
	String title = getStringDifferences(issue1.getTitle(), issue2.getTitle());
	if (title != null){
	    result.append("Title" + title + "\n");
	}
	String content = getStringDifferences(issue1.getContent(), issue2.getContent());
	if (content != null){
	    result.append("Content" + content + "\n");
	}
	if (issue1.getState() != issue2.getState()){
	    result.append(stateMessage);
	}
	return result.toString();
    }
    
    private static String getStringDifferences(String original, String modified){
	if(!original.equals(modified)){
	    return " has been modified";
	}
	return null;
    }
}
