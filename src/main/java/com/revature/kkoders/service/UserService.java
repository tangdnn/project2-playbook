package com.revature.kkoders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.kkoders.beans.UserImpl;
import com.revature.kkoders.dao.UserDAOImpl;
import com.revature.kkoders.dao.UserImplDAOImpl;

@Component
public class UserService {
	
	@Autowired
	UserImpl userInfo;

	@Autowired
	UserDAOImpl userDao;

	@Autowired
	UserImplDAOImpl userDAO;

	public void addUser(UserImpl newUser) {

		// DAO method
		userDAO.SignUpUser(newUser.getFirstName(), newUser.getLastName(), newUser.getUserName(), newUser.getPw(),
				newUser.getEmail(), newUser.getPicture(), newUser.getDesc());

		System.out.println("User added.");

	}
	
	public void updateUser(UserImpl currUser, UserImpl updatedUser){
		
		// store the user id
		updatedUser.setUserID(currUser.getUserID());
		updatedUser.setUserName(currUser.getUserName());
		updatedUser.setSteamId(currUser.getSteamId());

		// now we do a series of checks
		// if the first name has NOT been changed
		if (currUser.getFirstName().equals(updatedUser.getFirstName())) {
			updatedUser.setFirstName(currUser.getFirstName());
		}
		// if the last name has NOT been changed
		if (currUser.getLastName().equals(updatedUser.getLastName())) {
			updatedUser.setLastName(currUser.getLastName());
		}
		// if the password has NOT been changed
		if (updatedUser.getPw().length()==0) {
			updatedUser.setPw(currUser.getPw());
		}
		// if the email has NOT been changed
		if (currUser.getEmail().equals(updatedUser.getEmail())) {
			updatedUser.setEmail(currUser.getEmail());
		}
		
		userDAO.updateUser(updatedUser);
		
		System.out.println("user updated.");
	}

	public UserImpl auth(UserImpl usr) {
		if (userDao == null) {
			System.out.println("something??");
		}
		System.out.println(usr.getUserName() + " IN AUTH USRSERVICE");
		return userDao.validate(usr.getUserName(), usr.getPw());
	}
	

	public UserImpl getUserInfoByUserName(UserImpl user){
		
		userInfo = userDAO.getUserByUserName(user.getUserName());
		
		return userInfo;
	}
	
	public boolean confirmPassword(String enteredPw, UserImpl userInfo){
		boolean check = false;
		
		if (enteredPw.equals(userInfo.getPw())){
			check = true;
		}
		
		return check;
	}
}
