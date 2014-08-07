package internship.issuetracker.controller;

import internship.issuetracker.entities.Comment;
import internship.issuetracker.entities.Issue;
import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.CommentPojo;
import internship.issuetracker.pojo.IssuePojo;
import internship.issuetracker.service.CommentService;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.utils.ApplicationParameters;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private CommentService commentService;

    @RequestMapping(value = { "/createIssue" }, method = RequestMethod.GET)
    public String createIssuePage(Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        Issue issue = new Issue();
        model.addAttribute("user", user.getUserName());
        model.addAttribute("issue", issue);
        model.addAttribute("date", issue.getUpdateDate().toString().substring(0, 11));
        return "createIssue";
    }

    @RequestMapping(value = { "/createIssue" }, method = RequestMethod.POST)
    public String createIssuePage(@Valid Issue issue, HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "createIssue";
        issue.setOwner((User) request.getSession().getAttribute("user"));
        issueService.addIssue(issue);
        return "redirect:/issues";
    }

    @RequestMapping(value = { "/issuesChatRoom" })
    public String viewIssuesRoom() {
        // model.addAttribute("user", new User());
        return "chatRoomIssues";
    }

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String viewIssuePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("viewIssue", issueService.getIssue(id));
        return "viewIssue";
    }

    @RequestMapping(value = "/issue/{id}/api", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editIssue(@PathVariable Long id) {

        Map<String, Object> map = new HashMap<String, Object>();
        IssuePojo issue = issueService.getIssue(id);

        map.put("issue", issue);
        return map;
    }

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateIssue(@PathVariable Long id, @RequestBody @Valid Issue issue, BindingResult bindingResult) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            IssuePojo oldIssue = issueService.getIssue(id);
            map.put("issue", oldIssue);
            return map;
        }

        issue.setId(id);
        map.put("issue", "success");
        issueService.updateIssue(issue);
        return map;
    }

    @RequestMapping(value = "/issue/{id}/comment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addComment(@RequestBody @Valid Comment comment, @PathVariable Long id, BindingResult bindingResult, HttpServletRequest request) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<CommentPojo> pojoComments;
        IssuePojo issue = issueService.getIssue(id);
        User user = (User) request.getSession().getAttribute("user");
        Date currentDate = new Date();
        CommentPojo commentPojo = new CommentPojo(user.getUserName(), comment.getContent(), currentDate, issue.getId());

        if (!bindingResult.hasErrors()) {
            commentService.addComment(commentPojo);
            map.put("code", "success");
        } else {
            map.put("code", "error");
        }

        pojoComments = commentService.getCommentsForIssue(issue);
        map.put("comments", pojoComments);

        return map;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewIssuesPage(Model model) {

        List<IssuePojo> issuesListPojo = issueService.getOrderedIssues(1);

        model.addAttribute("issuesList", issuesListPojo);
        model.addAttribute("listLength", issueService.numberOfIssues());
        model.addAttribute("itemsPerPage", ApplicationParameters.ITEMS_PER_PAGE);
        if (issueService.numberOfIssues() % ApplicationParameters.ITEMS_PER_PAGE == 0) {
            model.addAttribute("pages", (int) (issueService.numberOfIssues() / ApplicationParameters.ITEMS_PER_PAGE));
        } else {
            model.addAttribute("pages", (int) (issueService.numberOfIssues() / ApplicationParameters.ITEMS_PER_PAGE + 1));
        }
        return "issues";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/issues";
    }
}
