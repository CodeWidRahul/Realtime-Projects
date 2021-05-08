package com.usermgmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usermgmt.entity.ForgetPassword;
import com.usermgmt.helper.Message;
import com.usermgmt.service.UserService;

@Controller
public class ForgetPasswordController {

	@Autowired
	private UserService userService;

	@RequestMapping("/forgetpassword")
	public String forgetPassword(Model model) {
		model.addAttribute("title", "Forgot Password : User Management");
		return "forgetpassword";
	}

	@PostMapping("/do_forgetpass")
	public String forgetPasswordByEmail(@ModelAttribute ForgetPassword forgetPassword, HttpSession session) {
		if (userService.isEmailUnique(forgetPassword.getEmail())) {
			session.setAttribute("forgetmsg", new Message("Email does not exist !!", "alert-danger"));
			return "forgetpassword";
		}

		boolean status = userService.forgetPasswordAndSendMail(forgetPassword.getEmail());

		if (!status) {
			session.setAttribute("forgetmsg", new Message("Something went wrong !!", "alert-danger"));
			return "forgetpassword";
		}
		session.setAttribute("forgetmsg", new Message(
				"Password send to your email successfully. Please check your email. Thank you!", "alert-success"));
		return "forgetpassword";
	}

}
