package com.usermgmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usermgmt.entity.Login;
import com.usermgmt.helper.Message;
import com.usermgmt.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Sign In! : User Management");
		return "login";
	}

	@PostMapping("/do_login")
	public String loginCheck(@ModelAttribute Login login, HttpSession session) {
		String loginCheck = userService.loginCheck(login.getEmail(), login.getPassword());
		if (loginCheck.equalsIgnoreCase("valid"))
			return "welcome";
		else {
			session.setAttribute("msg", new Message(loginCheck, "alert-danger"));
			return "login";
		}
	}
}
