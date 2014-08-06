package internship.issuetracker.utils;

import internship.issuetracker.entities.User;



public class UserName {
	private String userName;
	
	public UserName(User user)
	{
		userName=user.getUserName();
	}
	public UserName(){
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
