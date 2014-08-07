package internship.issuetracker.controller;

import internship.issuetracker.entities.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap model) {

        boolean isLoggedIn = false;
        User user = (User) request.getSession().getAttribute("user");
        log.log(Level.INFO, "checking for logged in:" + user);
        isLoggedIn = user != null;
        if (isLoggedIn) {
            return "redirect:/issues";
        }
        return "login";
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "home";
    }

}
