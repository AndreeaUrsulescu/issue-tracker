
package internship.issuetracker.controller;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.entities.Email;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.utils.MailMail;
import internship.issuetracker.validator.ActivationValidator;
import internship.issuetracker.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	
	@Autowired
	private ActivationValidator activationValidator;
	
	@Autowired
	private MailMail mail;
	
	@Autowired
	private ActivationService activationService;
	
	@Value("${ActivationEmailContentPart1}")
	 private String ActivationEmailContentPart1;
	
	@Value("${ActivationEmailContentPart2}")
	 private String ActivationEmailContentPart2;
	
	@Value("${ApplicationRoot}")
	 private String applicationRoot;
	
	@RequestMapping(method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute(new User());
		return "register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user,BindingResult bindingResult,HttpServletRequest request) {
	
		ModelAndView mv = new ModelAndView();

		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			mv.setViewName("register");
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		
		Activation activation=new Activation(user);
		
		activationValidator.validate(activation, bindingResult);
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("register");
			mv.addObject("errors", bindingResult.getAllErrors());
			return mv;
		}
		
		
		String msg=ActivationEmailContentPart1+applicationRoot+request.getContextPath()+"/activation/"+activation.getKeyHash()+ActivationEmailContentPart2;
		Email email=new Email();
		email.setTo(activation.getEmail());
		email.setSubject("Activation-issueTracker");	
		email.setContent(msg);
		mail.sendMail(email);
		activationService.addActivation(activation);
		mv.setViewName("checkEmailPage");
		return mv;
	}
	
	@RequestMapping(value = { "/checkEmailPage"}, method = RequestMethod.GET)
	public String checkEmailPage() {
		return "checkEmailPage";
	}
}
