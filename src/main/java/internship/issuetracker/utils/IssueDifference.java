package internship.issuetracker.utils;

import internship.issuetracker.entities.Issue;

import java.util.ArrayList;

import difflib.DiffUtils;
import difflib.Patch;


public class IssueDifference {
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
	    result.append("State has been changed\n");
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
