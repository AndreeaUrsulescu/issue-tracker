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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Issue viewIssuePage(@PathVariable("id") Long id) {
		return issueService.getIssue(id);
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
	@RequestMapping(method = RequestMethod.GET)
	public String viewIssuesPage() {
		return "issues";
	}
}
