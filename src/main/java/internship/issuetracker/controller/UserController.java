package internship.issuetracker.controller;

import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> viewAllUsers() {

        Map<String, Object> allUsers = new HashMap<>();
        List<UserPojo> allUsersPojo = userService.findAllUsers();
        allUsers.put("usersList", allUsersPojo);

        return allUsers;
    }
}
