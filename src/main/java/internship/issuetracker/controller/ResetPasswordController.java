package internship.issuetracker.controller;

import internship.issuetracker.entities.Email;
import internship.issuetracker.entities.ResetPassword;
import internship.issuetracker.entities.User;
import internship.issuetracker.service.ResetPasswordService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.utils.ApplicationParameters;
import internship.issuetracker.utils.EncryptData;
import internship.issuetracker.utils.MailHelper;
import internship.issuetracker.utils.UserName;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class ResetPasswordController {
    @Autowired
    private MailHelper mh;
    @Autowired
    private UserService userService;
    @Autowired
    private ResetPasswordService resetPasswordService;

    @RequestMapping(value = "/resetPassword/{hashPass}", method = RequestMethod.GET)
    public String resetPassword(@PathVariable String hashPass, Model model) {
        if (!resetPasswordService.existsResetPasswordForHash(hashPass))
            return "error";
        model.addAttribute(new UserName());
        return "resetPassword";
    }

    @RequestMapping(value = "/resetPassword/{hashPass}", method = RequestMethod.POST)
    public String resetPassword(@Valid UserName userName, @PathVariable String hashPass) {
        if (!resetPasswordService.existsResetPasswordForHash(hashPass))
            return "resetPasswordFailure";
        ResetPassword resetPassword = resetPasswordService.getResetPassword(hashPass);
        User user = userService.findUserByUserName(resetPassword.getOwner().getUserName());
        user.setPassword(EncryptData.sha256(userName.getUserName()));
        userService.updateUser(user);
        resetPasswordService.removeResetPassword(resetPassword);
        return "resetPasswordSuccess";
    }

    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute(new UserName());
        return "resetPasswordForm";
    }

    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.POST)
    public String resetPasswordForm(@Valid UserName userName) {
        if (!userService.exists(userName.getUserName()))
            return "resetPasswordFormFailure";
        User user = userService.findUserByUserName(userName.getUserName());
        ResetPassword resetPassword = new ResetPassword(user);
        if (!resetPasswordService.existsResetPasswordForUser(user)) {
            resetPasswordService.addResetPassword(resetPassword);
        }
        String msg = "To reset your password click the link below :\n" + ApplicationParameters.APPLICATION_ROOT + ApplicationParameters.CONTEXT_PATH + "/user/resetPassword/"
                + resetPassword.getKeyHash();
        Email email = new Email();
        email.setTo(user.getEmail());
        email.setSubject("Reset password-issueTracker");
        email.setContent(msg);
        mh.setUp(email);
        new Thread(mh).start();

        return "resetPasswordFormSuccess";
    }

}
