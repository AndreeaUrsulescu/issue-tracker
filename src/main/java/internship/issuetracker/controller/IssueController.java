package internship.issuetracker.controller;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserService userService;;

	@RequestMapping(value = { "/createIssue" }, method = RequestMethod.GET)
	public String createIssuePage(Model model, HttpServletRequest request) {
		
		User user=(User)request.getSession().getAttribute("user");
		model.addAttribute("user",user.getUserName());
		model.addAttribute("issue",new Issue());
		model.addAttribute("date",new Date());
		return "createIssue";
	}
	
	@RequestMapping(value = {"/createIssue"}, method = RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,HttpServletRequest request,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())
			return "createIssue";
		
		issue.setOwner((User)request.getAttribute("user"));
		issueService.addIssue(issue);
		return "redirect:/issues";
	}

	@RequestMapping(value = "issues/{id}", method = RequestMethod.GET)
	public String viewIssuePage(@PathVariable("id") Long id, Model model) {
		model.addAttribute(issueService.getIssue(id));
		return "viewIssue";
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
