package internship.issuetracker.controller;

import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.pojo.SearchParameter;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Autowired
	private LabelService labelService;
	
	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<IssuePojo> viewIssuesPage(@PathVariable("pageNumber") Integer pageNumber) {
		
		List<IssuePojo> issuesListPojo =  issueService.getOrderedIssues(pageNumber);
		return issuesListPojo;
	}
	
	@RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> search(@ModelAttribute SearchParameter searchParameters) {
		
		String searchCriteria = searchParameters.getSearchCriteria();
		int pageNumber = searchParameters.getPageNumber();
		String sortCriteria = searchParameters.getSortCriteria();
		String sortType = searchParameters.getSortType();
		
		List<IssuePojo> resultList = null;
		Map<String, Object> map = new HashMap<String,Object>();
		
		
		
		if (searchCriteria.equals("state")){
			resultList = searchService.findOrderedIssues(searchCriteria,searchParameters.getState(), pageNumber,sortCriteria,sortType);
			map.put("listLength",searchService.numberOfIssues(searchCriteria,searchParameters.getState()));
			}

		else{
			resultList = searchService.findOrderedIssues(searchCriteria,searchParameters.getInput(), pageNumber,sortCriteria,sortType);
			map.put("listLength",searchService.numberOfIssues(searchCriteria,searchParameters.getInput()));
			}
		
		map.put("issuesList", resultList);
		map.put("issuesPerPage",SearchRepository.itemsPerPage );
		
		return map;
	}
	
	@RequestMapping(value = "/labels", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelPojo> getLabels() {
		
		List<LabelPojo> issuesListPojo =  labelService.getLabels();
		return issuesListPojo;
	}
	

	@RequestMapping(value = "/issue/{issueId}/addLabel", method = RequestMethod.POST)
	@ResponseBody
	public void addLabel(@PathVariable("issueId") Long issueId,@RequestBody LabelPojo addLabel) {
			labelService.assignLabelToIssue(issueId,addLabel);
	}
}
