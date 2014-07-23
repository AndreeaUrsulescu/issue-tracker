package internship.issuetracker.controller;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.service.CommentService;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
		Issue issue = new Issue();
		model.addAttribute("user", user.getUserName());
		model.addAttribute("issue", issue);
		model.addAttribute("date",
				issue.getUpdateDate().toString().substring(0, 11));
		return "createIssue";
	}

	@RequestMapping(value = { "/createIssue" }, method = RequestMethod.POST)
	public String createIssuePage(@Valid Issue issue,
			HttpServletRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "createIssue";
		issue.setOwner((User) request.getSession().getAttribute("user"));
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
		IssuePojo pojoIssue = new IssuePojo(id, issue.getOwner().getUserName(),
				issue.getTitle(), issue.getContent(), issue.getUpdateDate(),
				issue.getState());

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
			IssuePojo pojoIssue = new IssuePojo(id, oldIssue.getOwner()
					.getUserName(), oldIssue.getTitle(), oldIssue.getContent(),
					oldIssue.getUpdateDate(), oldIssue.getState());
			map.put("issue", pojoIssue);
			return map;
		}

		map.put("issue", "success");

		Issue updatedIssue = issueService.getIssue(id);
		updatedIssue.setContent(issue.getContent());
		updatedIssue.setState(issue.getState());
		updatedIssue.setTitle(issue.getTitle());
		updatedIssue.setUpdateDate(currentDate);
		issueService.updateIssue(updatedIssue);
		return map;
	}

	@RequestMapping(value = "/issue/{id}/comment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addComment(@RequestBody @Valid Comment comment,
			@PathVariable Long id, BindingResult bindingResult,
			HttpServletRequest request) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<CommentPojo> pojoComments = new LinkedList<CommentPojo>();
		List<Comment> comments;
		Issue issue = issueService.getIssue(id);
		User user = (User) request.getSession().getAttribute("user");
		Date currentDate = new Date();

		comment.setCreationDate(currentDate);
		comment.setOwner(user);
		comment.setIssue(issue);

		if (!bindingResult.hasErrors()) {
		    commentService.addComment(comment);
		    map.put("code", "success");
		} else {
		    map.put("code", "error");
		}
		comments = commentService.getCommentsForIssue(issue);

		for (Comment com : comments) {
			CommentPojo pojoComment = new CommentPojo(user.getUserName(),
					com.getContent(), com.getCreationDate(), com.getIssue()
							.getId());
			pojoComments.add(pojoComment);
			
		}
		for(CommentPojo com: pojoComments){
		    System.out.println(com.getContent());
		}
		map.put("comments", pojoComments);
				
		
		return map;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewIssuesPage(Model model) {
		
		List<IssuePojo> issuesListPojo =  issueService.getOrderedIssues(1);
		
		model.addAttribute("issuesList", issuesListPojo);
		model.addAttribute("listLength",issueService.numberOfIssues());
		model.addAttribute("itemsPerPage", issueService.itemsPerPage() );
		
		return "issues";
	}
}
