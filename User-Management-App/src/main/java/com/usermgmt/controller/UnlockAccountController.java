package com.usermgmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usermgmt.entity.UnlockAccount;
import com.usermgmt.helper.AES256;
import com.usermgmt.helper.Message;
import com.usermgmt.service.UserService;

@Controller
public class UnlockAccountController {

	@Autowired
	private UserService userService;

	@RequestMapping("/unlockaccount")
	public String showUnlockAccount(@RequestParam(name = "e") String email, Model model) {
		model.addAttribute("title", "Unlock Account : User Management");
		model.addAttribute("email", AES256.decrypt(email));
		return "unlockaccount";
	}

	@PostMapping("/do_unlockacc")
	public String unlockAccount(@ModelAttribute UnlockAccount unlockAccount, Model model, HttpSession session) {
		boolean tempPasswordValid = userService.isTempPasswordValid(unlockAccount.getEmail(),
				unlockAccount.getTempPassword());
		if (!tempPasswordValid) {
			model.addAttribute("email", unlockAccount.getEmail());
			session.setAttribute("unlockaccmsg", new Message("Temporary password is incorrect !!", "alert-danger"));
			return "/unlockaccount";
		} else {
			if (!unlockAccount.getCnfPassword().equals(unlockAccount.getNewPassword())) {
				model.addAttribute("email", unlockAccount.getEmail());
				session.setAttribute("unlockaccmsg", new Message("Password mismatch !!", "alert-danger"));
				return "/unlockaccount";
			} else {
				boolean result = userService.unlockAccountAndUpdateStatus(unlockAccount);
				if (result) {
					session.setAttribute("unlockaccmsg", new Message(
							"Your account has been unlocked successfully please login now.", "alert-success"));
					return "/unlockaccount";
				} else {
					model.addAttribute("email", unlockAccount.getEmail());
					session.setAttribute("unlockaccmsg", new Message("Something went wrong !!", "alert-danger"));
					return "/unlockaccount";
				}
			}
		}
	}
}
