package com.usermgmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		Object obj = userService.loginCheck(login.getEmail(), login.getPassword());
		if (obj instanceof String) {
			session.setAttribute("msg", new Message((String) obj, "alert-danger"));
			return "login";
		} else {
			session.setAttribute("user", obj);
			return "welcome";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (!session.isNew()) {
			session.removeAttribute("user");
			session.invalidate();
			return "login";
		}
		return null;
	}
}
