package internship.issuetracker.controller;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/activation")
public class ActivationController {
	@Autowired
	private ActivationService activationService;
	
	@Value("${itemsPerPage}")
	String items;

	@Value("${StateHasBeenChangedMessage}")
	String state;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{hashKey}",method=RequestMethod.GET)
	public String activate (@PathVariable String hashKey)
	{
		Activation activation=activationService.getActivation(hashKey);
		if(null==activation)
			return "activationFailure";
		else
		{
			User user=activation.getUserFromActivation();
			userService.addUser(user);
			activationService.removeActivation(activation);
			return "activationSuccess";
		}
	}
	@RequestMapping(value="/activationSuccess",method=RequestMethod.GET)
	public String activationSuccess ()
	{
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(items);
		System.out.println(state);
		return "activationSuccess";
	}
	
	@RequestMapping(value="/activationFailure",method=RequestMethod.GET)
	public String activationFailure ()
	{
		return "activationFailure";
	}
	
	@RequestMapping(value = { "/rteTest" })
	public String rte() {
		
		return "rteTest";
	}
}
