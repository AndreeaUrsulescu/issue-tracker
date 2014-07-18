package internship.issuetracker.controller;

import java.util.Date;

import javax.validation.Valid;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/issue")
public class IssueController {
	@Autowired
	private IssueService issueService;
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public String createIssuePage(Model model)
	{
		return "newIssue";
	}
	
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
			return "newIssue";
		
		User user=new User();
		user.setEmail("user@user.com");
		user.setPassword("password");
		user.setUserName("username");
		issue.setOwner(user);
		issue.setUpdate_date(new Date(2009,12,9));
		return "register";
	}
}
