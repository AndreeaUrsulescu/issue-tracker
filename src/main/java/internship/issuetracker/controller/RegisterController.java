package internship.issuetracker.controller;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.entities.Email;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.utils.ApplicationParameters;
import internship.issuetracker.utils.MailHelper;
import internship.issuetracker.validator.ActivationValidator;
import internship.issuetracker.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {
    static private String msg1 = "To activate your account please click the link below \n\n";
    static private String msg2 = "\n\n Thank you for your interest.";

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ActivationValidator activationValidator;

    @Autowired
    private MailHelper mh;
    @Autowired
    private ActivationService activationService;

   
    @RequestMapping(method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            mv.setViewName("register");
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }

        Activation activation = new Activation(user);

        activationValidator.validate(activation, bindingResult);

        if (bindingResult.hasErrors()) {
            mv.setViewName("register");
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }

        String msg = msg1 + ApplicationParameters.APPLICATION_ROOT + request.getContextPath() + "/activation/" + activation.getKeyHash() + msg2;
        Email email = new Email();
        email.setTo(activation.getEmail());
        email.setSubject("Activation-issueTracker");
        email.setContent(msg);

        mh.setUp(email);
        new Thread(mh).start();
        activationService.addActivation(activation);
        mv.setViewName("checkEmailPage");
        return mv;
    }

    @RequestMapping(value = { "/checkEmailPage" }, method = RequestMethod.GET)
    public String checkEmailPage() {
        return "checkEmailPage";
    }
}
