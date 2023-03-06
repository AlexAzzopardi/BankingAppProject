package com.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.group.entity.Account;
import com.group.entity.User;
import com.group.model.service.UserService;

@Controller
@SessionAttributes({"user"})
public class UserController {

	String currentUser;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView getLoginPage() {
		return new ModelAndView("Login", "user", new User());
	}
	
	@RequestMapping("/loginUser")
	public ModelAndView loginCheck(@ModelAttribute("user") User userInput) {
		User user = userService.loginUser(userInput);
		if(user != null) {
			currentUser = user.getUserName();
			return new ModelAndView("MainMenu", "user", user);
		}
		else 
			return new ModelAndView("Login", "Message", "Login Failed!");
	}
	
	@RequestMapping("/addUserPage")
	public ModelAndView addUserPageController() {
		return new ModelAndView("AddUser", "user", new User());
	}
	
	@RequestMapping("/addUser")
	public ModelAndView addUserController(@ModelAttribute("user") User user) {
		if (userService.getUserbyUserName(user.getUserName()) == null) 
			userService.addUser(user);
		return getLoginPage();
	}
}
