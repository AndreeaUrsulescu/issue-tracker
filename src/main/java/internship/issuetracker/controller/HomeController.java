package internship.issuetracker.controller;

import internship.issuetracker.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {""})
    public String home(ModelMap model) {
    	
		model.addAttribute("user", new User());
		
        return "home";
    }
    
    /*@RequestMapping(value = {"/register"})
    public String register() {
    	
    	return "register";
    	
    } */
}
