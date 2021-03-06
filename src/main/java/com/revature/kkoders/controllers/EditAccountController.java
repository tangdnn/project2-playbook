package com.revature.kkoders.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.revature.kkoders.beans.UserImpl;
import com.revature.kkoders.dao.UserImplDAOImpl;
import com.revature.kkoders.service.UserService;

@Controller
@RequestMapping(value = { "/account/edit" })
public class EditAccountController {

	@Autowired
	UserImpl currUser;
	
	@Autowired
	UserImpl updated;

	@Autowired
	UserImplDAOImpl userdao;

	@Autowired
	UserService service;

	/**
	 * Brings up the edit account page of the user signed in.
	 * 
	 * Browses to the edit account page only if a user is signed in. Otherwise
	 * browses to the login page.
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView doEditAccount(@ModelAttribute("updatedUser") UserImpl updatedUser, HttpSession session) {
		// TODO: validate that user is logged in to display edit account page,
		// otherwise send to login page.

		currUser = (UserImpl) session.getAttribute("alsoUser");

		updatedUser = currUser;

		return new ModelAndView("account/edit_account", "updatedUser", updatedUser); 
	}

	@RequestMapping(method = RequestMethod.POST)
	public String changeAccount(@ModelAttribute("updatedUser") UserImpl updatedUser, HttpSession session) {

		currUser = (UserImpl) session.getAttribute("alsoUser");
		
		updated = service.updateUser(currUser, updatedUser);
		
		session.removeAttribute("alsoUser");
		session.setAttribute("alsoUser", updated);

		return "account/edit_account";
	}

}