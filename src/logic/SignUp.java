package logic;

import data.UserData;
import entities.User;

public class SignUp {
	
	private UserData userData;
	
	public SignUp() {
		userData = new UserData();
	}
	
	public boolean checkEmailAvailability(String email) {
		User user = userData.getByEmail(email);
		return (user != null); 
	}
	
	public int addNewUser(User newUser) {
		return userData.add(newUser);

	}
}
