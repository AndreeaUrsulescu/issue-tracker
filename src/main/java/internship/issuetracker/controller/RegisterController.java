package internship.issuetracker.controller;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute(new User());
		return "/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		
		if (bindingResult.hasErrors())
			return "/register";
		
		userService.addUser(user);
		return "redirect:/index";
	}
}
