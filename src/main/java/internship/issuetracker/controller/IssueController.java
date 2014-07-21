package internship.issuetracker.controller;

import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/issues")
public class IssueController {
	@Autowired
	private IssueService issueService;

	@Autowired
	private UserService userService;;

	@RequestMapping(value = { "/createIssue" }, method = RequestMethod.GET)
	public String createIssuePage(Model model, HttpServletRequest request) {
		Issue issue = new Issue();
		Principal principal = request.getUserPrincipal();
		if (null == principal)
			return "redirect:/issues";
		
		issue.setOwner(userService.findUserByUserName(principal.getName()));
		issue.setUpdateDate(new Date());
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewIssuePage(@PathVariable("id") Long id, Model model) {
		model.addAttribute(issueService.getIssue(id));
		return "viewIssue";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateIssue(@PathVariable Long id, @RequestBody @Valid Issue issue, BindingResult bindingResult) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (bindingResult.hasErrors())
		{
			map.put("issue", issueService.getIssue(id));
			return map;
		}
		
		map.put("issue", "success");
		issueService.updateIssue(issue);
		return map;
	}
}
