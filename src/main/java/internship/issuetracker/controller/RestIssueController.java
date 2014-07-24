package internship.issuetracker.controller;

import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.SearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/issues")
public class RestIssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<IssuePojo> viewIssuesPage(@PathVariable("pageNumber") Integer pageNumber) {
		
		List<IssuePojo> issuesListPojo =  issueService.getOrderedIssues(pageNumber);
		return issuesListPojo;
	}
	
	@RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> search(@RequestBody ArrayList<Object> sortParameters) {
		
		String searchCriteria = (String) sortParameters.get(0);
		Integer pageNumber = (Integer) sortParameters.get(1);
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<IssuePojo> resultList = searchService.findOrderedIssues(searchCriteria, pageNumber);
		map.put("issuesList", resultList);
		map.put("listLength",searchService.numberOfIssues(searchCriteria));
		map.put("issuesPerPage",SearchRepository.itemsPerPage );
		
		return map;
	}
	
}
