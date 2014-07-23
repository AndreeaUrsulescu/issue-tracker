package internship.issuetracker.controller;

import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.service.IssueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/issues")
public class RestIssueController {

	@Autowired
	private IssueService issueService;
	
	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<IssuePojo> viewIssuesPage(@PathVariable("pageNumber") Integer pageNumber) {
		
		List<IssuePojo> issuesListPojo =  issueService.getOrderedIssues(pageNumber);
		return issuesListPojo;
	}
	
}
