package com.smartcontact.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontact.entity.Contact;
import com.smartcontact.entity.User;
import com.smartcontact.service.UserService;

@Controller
public class MyController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/")
	public String getForm(Model model) {
		model.addAttribute("contact",new Contact());
		model.addAttribute("user",new User());
		return "home";
	}
	
	@GetMapping("/register")
	public String reg(Model model) {
		model.addAttribute("user",new User());
		return "registration";
	}
	
	@GetMapping("/signin")
	public String login(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	
	@PostMapping("/reg")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult rs, Model model, @RequestParam(value="termconditions",defaultValue = "true")
	boolean termconditions, HttpSession session) {
		if(rs.hasErrors()) {
			System.out.println(user);
			System.out.println(rs);
			model.addAttribute("user", user);
			return "registration";
		}
		User u=userService.saveUser(user);
		if(u!=null) {
			model.addAttribute("sucess", "Successfully Registred.");
			return "registration";
		}else {
			model.addAttribute("error", "Somthing went wrong.");
			return "registration";
		}
		
	} 
}
