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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IssueController {
	@Autowired
	private IssueService issueService;

	@RequestMapping(value = {"/createIssue"}, method = RequestMethod.GET)
	public String createIssuePage(Model model) {
		Issue issue = new Issue();
		
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user;
		if (o != null)
			user = (User) o;
		else
			return "home";
		
		issue.setUpdateDate(new Date());
		issue.setOwner(user);
		model.addAttribute(issue);

		return "createIssue";

	}

	@RequestMapping(value = {"/createIssue"}, method = RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "createIssue";

		issueService.addIssue(issue);
		return "redirect:/issues";
	}
	
	@RequestMapping(value = "/issues/{id}", method = RequestMethod.PUT)
	public String updateIssue(@PathVariable Long id, @Valid Issue issue, BindingResult bindingResult, Model model) {
		Issue initialIssue;
		
		if (bindingResult.hasErrors())
		{
			initialIssue = issueService.getIssue(id);
			model.addAttribute(initialIssue);
			return "viewIssue";
		}
		
		issueService.updateIssue(issue);
		return "redirect:/issues";
	}
}
