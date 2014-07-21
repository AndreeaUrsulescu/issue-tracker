package internship.issuetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommentsController {

	@RequestMapping(value={"/comments"})
	public String comments(ModelMap model){
		
		return "comments";
		
	}
	
	
}
