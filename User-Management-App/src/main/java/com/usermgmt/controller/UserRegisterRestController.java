package com.usermgmt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermgmt.entity.User;
import com.usermgmt.helper.Message;
import com.usermgmt.service.UserService;

@RestController
public class UserRegisterRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/do_register")
	public ModelAndView registerUser(@ModelAttribute User user, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries", userService.getCountries());
		if (userService.isEmailUnique(user.getEmail())) {
			boolean saveUser = userService.saveUser(user);
			if (saveUser) {
				modelAndView.setViewName("register");
				session.setAttribute("message", new Message("Registration Successful !! Please check your email '"
						+ user.getEmail() + "' to unlock your account.", "alert-success"));
				model.addAttribute("user", new User());
			} else {
				modelAndView.setViewName("register");
				session.setAttribute("message", new Message("Something went wrong !", "alert-danger"));
				model.addAttribute("user", user);
			}
		} else {
			modelAndView.setViewName("register");
			session.setAttribute("message",
					new Message("This email '" + user.getEmail() + "' already registered !", "alert-danger"));
			model.addAttribute("user", user);
		}
		return modelAndView;
	}

	@GetMapping("/states")
	public @ResponseBody String getStatesByCountryId(@RequestParam Integer countryId) {
		String json = null;
		List<Object[]> list = userService.getStates(countryId);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(json);
		return json;

	}

	@GetMapping("/cities")
	public @ResponseBody String getCitiesByStateId(@RequestParam Integer stateId) {
		String json = null;
		List<Object[]> list = userService.getCities(stateId);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
