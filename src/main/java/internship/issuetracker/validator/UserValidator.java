package internship.issuetracker.validator;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    //Can this Validator validate instances of the supplied clazz
    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors error) {
	ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName", "username.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "email.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "password.empty");
	User p = (User) object;
	String trimedUserName = p.getUserName().trim();
	if (userService.exists(trimedUserName.toLowerCase()) == true) {
	    error.rejectValue("userName", "username.exists");
	}
    }

}
