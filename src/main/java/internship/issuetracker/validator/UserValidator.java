package internship.issuetracker.validator;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Autowired
    private UserService userRepo;
    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors error) {
	ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        User p = (User) object;
        if (userRepo.exists(p.getUserName())) {
            error.rejectValue("username", "already exists");
        }	
    }


}
