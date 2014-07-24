
package internship.issuetracker.controller;

import internship.issuetracker.entities.User;
import internship.issuetracker.service.UserService;
import internship.issuetracker.validator.UserValidator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user,
			BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("register");
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		userService.addUser(user);
		mv.setViewName("redirect:/login");
		return mv;
	}
}
