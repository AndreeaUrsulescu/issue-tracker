package internship.issuetracker.controller;

import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ResetPasswordService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.utils.EncryptData;
import internship.issuetracker.utils.UserName;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResetPasswordController {

	@Autowired
	private UserService userService;
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	@RequestMapping(value = "/resetPassword/{hashPass}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable String hashPass) {
		System.out.println(hashPass);

		return "login";
	}

	@RequestMapping(value = "/resetPasswordForm", method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute(new UserName());
		return "resetPasswordForm";
	}

	@RequestMapping(value = "/resetPasswordForm", method = RequestMethod.POST)
	public String resetPasswordForm(@Valid UserName userName) {
		System.out.println(userName.getUserName());
		User user=userService.findUserByUserName(userName.getUserName());
		if(!resetPasswordService.existsResetPasswordForUser(user)){
		ResetPassword resetPassword=new ResetPassword(user);
		resetPasswordService.addResetPassword(resetPassword);
		}
		return "login";
	}
}
