package internship.issuetracker.service;

import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.Hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
    	String hashPassword = Hash.sha256(user.getPassword());
    	user.setPassword(hashPassword);
    	String aux;
    	
	this.userRepository.create(user);
    }

    public void updateUser(User user) {
	this.userRepository.update(user);
    }

    public boolean exists(String userName) {
	return this.userRepository.exists(userName);
    }
    
    public boolean matchPassword(String userName,String password){
    	String hashPassword = Hash.sha256(password);
		return this.userRepository.matchPassword(userName, hashPassword);
	}
}
