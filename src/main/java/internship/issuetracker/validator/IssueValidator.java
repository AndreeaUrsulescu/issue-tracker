package internship.issuetracker.validator;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.enums.State;
import internship.issuetracker.service.UserService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class IssueValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Issue.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors error) {
		Issue issue = (Issue) object;

		if (issue.getState().equals(State.Closed)) {
			error.reject("You can't edit with state Closed");
		}

		
		if (issue.getUpdateDate() == null)
			error.rejectValue("updateDate", "updateDate.incorrect",
					"Date field is missing");
		else
			if (issue.getUpdateDate().compareTo(new Date()) > 0) {
				error.rejectValue("updateDate", "updateDate.incorrect",
						"Date field is incorrect");
			}
		
		
	}

}
