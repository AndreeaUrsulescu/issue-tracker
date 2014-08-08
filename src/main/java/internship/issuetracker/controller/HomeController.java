package internship.issuetracker.controller;

import internship.issuetracker.entities.User;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "", "/home" })
    public String home(HttpServletRequest request, ModelMap model) {
        boolean isLoggedIn = false;
        User user = (User) request.getSession().getAttribute("user");
        isLoggedIn = user != null;
        if (isLoggedIn) {
            return "redirect:/issues";
        }
       
        model.addAttribute("user", new User());
        return "home";
    }

}
