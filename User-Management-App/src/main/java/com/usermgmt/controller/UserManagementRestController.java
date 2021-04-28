package com.usermgmt.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserManagementRestController {

	@RequestMapping("/")
	public ModelAndView home(Model model) {
		model.addAttribute("title", "Home : User Management");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping("/login")
	public ModelAndView login(Model model) {
		model.addAttribute("title", "Sign In! : User Management");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping("/register")
	public ModelAndView register(Model model) {
		model.addAttribute("title", "Sign Up! : User Management");
		ModelAndView modelAndView = new ModelAndView();
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
	
	@RequestMapping("/unlockaccount")
	public ModelAndView unlockAccount(Model model) {
		model.addAttribute("title", "Unlock Account : User Management");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unlockaccount");
		return modelAndView;
	}

}
