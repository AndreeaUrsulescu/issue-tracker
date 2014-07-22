package internship.issuetracker.controller;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.service.CommentService;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	private UserService userService;
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = { "/createIssue" }, method = RequestMethod.GET)
	public String createIssuePage(Model model, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user.getUserName());
		model.addAttribute("issue", new Issue());
		model.addAttribute("date", new Date());
		return "createIssue";
	}

	@RequestMapping(value = { "/createIssue" }, method = RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,
			HttpServletRequest request, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "createIssue";

		issue.setOwner((User) request.getAttribute("user"));
		issueService.addIssue(issue);
		return "redirect:/issues";
	}

	@RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
	public String viewIssuePage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("viewIssue", issueService.getIssue(id));
		return "viewIssue";
	}
	
	@RequestMapping(value = "/api/issue/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> editIssue(@PathVariable Long id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Issue issue = issueService.getIssue(id);
		IssuePojo pojoIssue = new IssuePojo(issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(),
				issue.getUpdateDate(), issue.getState());
		
		map.put("issue", pojoIssue); 
		return map;
	}
	
	@RequestMapping(value = "/issue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateIssue(@PathVariable Long id,
			@RequestBody @Valid Issue issue, BindingResult bindingResult) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Date currentDate = new Date();
		
		issue.setUpdateDate(currentDate);


@RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
public String viewIssuePage(@PathVariable("id") Long id, Model model) {
model.addAttribute("viewIssue", issueService.getIssue(id));
return "viewIssue";
}


@RequestMapping(value = "/api/issue/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> editIssue(@PathVariable Long id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Issue issue = issueService.getIssue(id);
		IssuePojo pojoIssue = new IssuePojo(issue.getOwner().getUserName(), issue.getTitle(), issue.getContent(),
				issue.getUpdateDate(), issue.getState());
		
		map.put("issue", pojoIssue); 
		return map;
	}

@RequestMapping(value = "/issue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateIssue(@PathVariable Long id,
			@RequestBody @Valid Issue issue, BindingResult bindingResult) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Date currentDate = new Date();
		
		issue.setUpdateDate(currentDate);

		if (bindingResult.hasErrors()) {
			Issue oldIssue = issueService.getIssue(id);
			IssuePojo pojoIssue = new IssuePojo(oldIssue.getOwner().getUserName(), oldIssue.getTitle(), oldIssue.getContent(), 
					oldIssue.getUpdateDate(), oldIssue.getState());
			map.put("issue", pojoIssue);
			return map;
		}

		map.put("issue", "success");
		
		Issue oldIssue = issueService.getIssue(id);
		oldIssue.setContent(issue.getContent());
		oldIssue.setState(issue.getState());
		oldIssue.setTitle(issue.getTitle());
		oldIssue.setUpdateDate(currentDate);
		issueService.updateIssue(oldIssue);
		return map;
	}
	@ResponseBody
	public Map<String, Object> addComment(@RequestBody @Valid Comment comment, @PathVariable Long id, BindingResult bindingResult,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommentPojo> pojoComments = new ArrayList<CommentPojo>();
		List<Comment> comments;
		Issue issue = issueService.getIssue(id);
		User user = (User) request.getSession().getAttribute("user");
		Date currentDate = new Date();
		
		comment.setCreationDate(currentDate);
		comment.setOwner(user);
		comment.setIssue(issue);
		
		if (bindingResult.hasErrors()) {
			comments = commentService.getCommentsForIssue(issue);
			
			for (Comment com : comments) {
				CommentPojo pojoComment = new CommentPojo(user.getUserName(), com.getContent(), com.getCreationDate(), 
						com.getIssue().getId());
				pojoComments.add(pojoComment);
			}
			
			map.put("comments", pojoComments);
			return map;
		}
			
		commentService.addComment(comment);
		comments = commentService.getCommentsForIssue(issue);
		
		for (Comment com : comments) {
			CommentPojo pojoComment = new CommentPojo(user.getUserName(), com.getContent(), com.getCreationDate(),
					com.getIssue().getId());
			pojoComments.add(pojoComment);
		}
			
		map.put("comments", pojoComments);
		return map;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewIssuesPage(Model model) {
		List<Issue> issuesList = issueService.getOrderedIssues();
		model.addAttribute("issuesList", issuesList);
		return "issues";
	}
}
