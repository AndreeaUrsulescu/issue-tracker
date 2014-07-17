package internship.issuetracker.service;


import java.util.List;

import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository ;
	
	@Autowired
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public void addUser(User user){
		this.userRepository.create(user);
	}
	
	public void removeUser(User user){
		this.userRepository.remove(user);
	}
	
	public void updateUser(User user){
		this.userRepository.update(user);
	}
	
	public User findUser(Long id){
		return this.userRepository.find(id);
	}
	
	public List<User> findUsers(){
		return this.userRepository.getAll();
	}
}

