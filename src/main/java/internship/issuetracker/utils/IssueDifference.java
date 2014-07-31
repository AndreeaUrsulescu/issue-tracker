package internship.issuetracker.utils;

import internship.issuetracker.entities.Issue;

public class IssueDifference {
	public static String generateDifference(Issue newIssue, Issue oldIssue) {
		String title = getStringDifferences(newIssue.getTitle(), oldIssue.getTitle());
		String content = getStringDifferences(newIssue.getContent(), oldIssue.getContent());
		String msg = ApplicationParameters.changedIssueEmail + newIssue.getId();
		
		StringBuilder result = new StringBuilder();

		if (null != title) {
			result.append("Title").append(title).append(" from \"").append(oldIssue.getTitle()).append("\" to \"").append(newIssue.getTitle()).append("\" .\n");
		}

		if (null != content ) {
			result.append("Content").append(content).append(" .\n");
		}
		if (newIssue.getState() != oldIssue.getState()) {
			result.append(ApplicationParameters.stateHasBeenChangedMessage).append(" from \"").append(oldIssue.getState()).append("\" to \"").append(newIssue.getState()).append("\" .\n");
		}

		return result.append(msg).toString();
	}

	private static String getStringDifferences(String original, String modified) {
		if (!original.equals(modified)) {
			return " has been modified";
		}
		return null;
	}

}
