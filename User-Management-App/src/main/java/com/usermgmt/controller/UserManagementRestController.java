package com.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.usermgmt.entity.User;
import com.usermgmt.service.UserService;

@RestController
public class UserManagementRestController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView home(Model model) {
		model.addAttribute("title", "Home : User Management");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping("/register")
	public ModelAndView register(Model model) {
		model.addAttribute("title", "Sign Up! : User Management");
		model.addAttribute("user", new User());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries", userService.getCountries());
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping("/forgetpassword")
	public ModelAndView forgetPassword(Model model) {
		model.addAttribute("title", "Forgot Password : User Management");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forgetpassword");
		return modelAndView;
	}


}
