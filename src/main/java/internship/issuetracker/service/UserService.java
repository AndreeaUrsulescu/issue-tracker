package internship.issuetracker.service;

import internship.issuetracker.entities.User;
import internship.issuetracker.pojo.UserPojo;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.EncryptData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        this.userRepository.create(user);
        LOG.log(Level.INFO, "User " + user.getUserName() + " was registered");
    }

    public void updateUser(User user) {
        this.userRepository.update(user);
        LOG.log(Level.INFO, "User " + user.getUserName() + " was updated!");
    }

    public boolean exists(String userName) {
        return this.userRepository.exists(userName);
    }

    public boolean matchPassword(String userName, String password) {
        String hashPassword = EncryptData.sha256(password);
        return this.userRepository.matchPassword(userName, hashPassword);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public List<UserPojo> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserPojo> allUsersPojo = new ArrayList<>();

        if (allUsers.isEmpty()) {
            LOG.log(Level.INFO, "There are no users");
        }
        for (int index = 0; index < allUsers.size(); index++) {
            User userEntity = allUsers.get(index);
            UserPojo userPojo = new UserPojo();
            userPojo.setUserName(userEntity.getUserName());
            allUsersPojo.add(index, userPojo);
        }
        return allUsersPojo;
    }
}
