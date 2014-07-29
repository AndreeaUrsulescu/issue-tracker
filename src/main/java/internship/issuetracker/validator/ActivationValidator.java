package internship.issuetracker.validator;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.service.ActivationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ActivationValidator implements Validator{
	
	@Autowired
	private ActivationService activationService;
	
	@Override
	public boolean supports(Class<?> clas) {
		return Activation.class.equals(clas);
	}

	@Override
	public void validate(Object act, Errors error) {
		Activation activation=(Activation) act;
		
		if(null!=activationService.getActivation(activation.getKeyHash()))
			error.rejectValue("userName","username.exists");
	}

}
