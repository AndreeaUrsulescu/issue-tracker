package internship.issuetracker.utils;

import static org.junit.Assert.*;
import internship.issuetracker.entities.Issue;

import org.junit.Test;

public class IssueDifferenceTest {

    @Test
    public void testGenerateDifference() {
       Issue oldIssue =new Issue();
       Issue newIssue =new Issue();
       oldIssue.setTitle("old");
       newIssue.setTitle("old");
       oldIssue.setContent("c");
       newIssue.setContent("n");
       String result = IssueDifference.generateDifference(newIssue, oldIssue);
       String expResult = "Content has been modified .\n\n\n\nFor the issue :\n\nhttp://localhost:8080/issue-tracker/issues/issue//issue-tracker/issues/issue/null";

       assertTrue(result.equals(expResult));
    }

}
