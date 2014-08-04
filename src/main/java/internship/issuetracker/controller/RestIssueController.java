package internship.issuetracker.controller;

import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.pojo.LabelPojo;
import internship.issuetracker.pojo.MultipleSearchParameter;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.repository.SearchRepository;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.LabelService;
import internship.issuetracker.service.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public List<IssuePojo> viewIssuesPage(
			@PathVariable("pageNumber") Integer pageNumber) {

		List<IssuePojo> issuesListPojo = issueService
				.getOrderedIssues(pageNumber);
		return issuesListPojo;
	}

	@RequestMapping(value = "/labels", method = RequestMethod.GET)
	@ResponseBody
	public Map<Long, String> getLabels() {

		Map<Long, String> labels = new HashMap<>();
		for (LabelPojo label : labelService.getAllLabels()) {
			labels.put(label.getId(), label.getLabelName());
		}
		return labels;
	}

	@RequestMapping(value = "/issue/{issueId}/addLabel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addLabel(@PathVariable("issueId") Long issueId,
			@RequestBody LabelPojo addLabel) {
		boolean exists = labelService.assignLabelToIssue(issueId, addLabel);
		Map<String, Object> map = new HashMap<String, Object>();
		if (exists) {
			map.put("response", "success");
		} else {
			map.put("response", "duplicate");
		}
		return map;
	}

	@RequestMapping(value = "/issue/{id}/removeLabel", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> removeLabel(@PathVariable Long id,
			@RequestBody LabelPojo labelToRemove) {
		Map<String, Object> map = new HashMap<String, Object>();
		labelService.removeLabelFromIssue(id, labelToRemove);
		map.put("response", "success");
		return map;
	}

	@RequestMapping(value = "/issue/{issueId}/assignUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> assignUser(
			@PathVariable("issueId") Long issueId,
			@RequestBody UserPojo assignedUser) {
		Map<String, Object> response = new HashMap<>();
		issueService.assignUserToIssue(issueId, assignedUser);
		response.put("response", "success");
		return response;
	}

	@RequestMapping(value = "/issue/{issueId}/unassignUser", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> unassignUser(
			@PathVariable("issueId") Long issueId) {
		Map<String, Object> response = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if (issueService.unassignUserToIssue(issueId,username)){
			response.put("response", "success");
		} else {
			response.put("response", "failure");
		}
		return response;
	}

	@RequestMapping(value = "/multipleSearchBy", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> multipleSearch(
			@ModelAttribute MultipleSearchParameter searchParameters) {

		List<IssuePojo> resultList = null;
		Map<String, Object> map = new HashMap<String, Object>();

		resultList = searchService.multiplePredicates(searchParameters);
		map.put("issuesList", resultList);
		map.put("listLength",
				searchService.numberOfIssuesMultipleSearch(searchParameters));
		map.put("issuesPerPage", SearchRepository.itemsPerPage);

		return map;
	}
}
