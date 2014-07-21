package internship.issuetracker.controller;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@Autowired
	private UserService userService;;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createIssuePage(Model model, HttpServletRequest request) {
		Issue issue = new Issue();
		Principal principal=request.getUserPrincipal();
				
		if(null==principal)
			return "redirect:/issues";
		
		
		issue.setOwner(userService.findUserByUserName(principal.getName()));
		System.out.println(principal.getName());
		issue.setUpdateDate(new Date());
		model.addAttribute(issue);
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
