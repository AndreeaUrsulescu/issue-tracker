package internship.issuetracker.controller;

import java.util.Date;

import javax.validation.Valid;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.IssueService;

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
		Issue issue=new Issue();
		User user=new User(); 
		user.setEmail("user@user.com");   // the logged User (when spring security is done)
		user.setPassword("password");
		user.setUserName("username");
		issue.setUpdateDate(new Date());
		issue.setOwner(user);
		model.addAttribute(issue);
		
		return "newIssue";
		
	}
	
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
			return "newIssue";
		
		issueService.addIssue(issue);
		return "redirect:/index";    // or whatever
	}
}
