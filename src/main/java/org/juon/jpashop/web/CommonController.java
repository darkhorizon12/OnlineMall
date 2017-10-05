package org.juon.jpashop.web;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	
	
	@RequestMapping(value = "/")
	public String goMain(Principal principal, Authentication auth) {
		if (principal != null && auth != null) {
			String name = principal.getName();
			UserDetails user = (UserDetails) auth.getPrincipal();
			String username = user.getUsername();
		}
		return "/home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "/login";
	}

	@RequestMapping("/loginError")
	public String databaseError(Model model) {
		model.addAttribute("code", "LOGIN FAILED. TRY AGAIN!");
		
		return "/login";
	}
}
