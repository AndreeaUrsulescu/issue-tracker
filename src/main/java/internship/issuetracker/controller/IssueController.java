package internship.issuetracker.controller;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.IssueService;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/createIssue")
public class IssueController {
	@Autowired
	private IssueService issueService;

	@RequestMapping(method = RequestMethod.GET)
	public String createIssuePage(Model model) {
		Issue issue = new Issue();
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user;
		if(o instanceof User)
		System.out.println("x"+o.getClass());
		else
		System.out.println("y"+o.getClass());
		if (o != null)
			user = (User) o;
		else
			return "home";
		
		issue.setUpdateDate(new Date());
		issue.setOwner(user);
		model.addAttribute(issue);
		
		/*
		User user=new User();
		user.setUserName("username");
		issue.setOwner(user);
		model.addAttribute(issue);
		*/
		return "createIssue";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "createIssue";

		issueService.addIssue(issue);
		return "redirect:/issues";
	}
}
