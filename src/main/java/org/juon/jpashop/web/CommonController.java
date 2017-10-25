package org.juon.jpashop.web;

import java.security.Principal;
import java.util.List;

import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController extends BasicController{
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value = "/")
	public String goMain(Principal principal, Authentication auth, Model model) {
		if (principal != null && auth != null) {
			String name = principal.getName();
			UserDetails user = (UserDetails) auth.getPrincipal();
			String username = user.getUsername();
		}
		Pageable pageable = createPage(1);
		final Page<Item> items = itemService.findAll(pageable);
		final List<Item> mainItems = itemService.fifindMoreExpensiveThanPrice(19_000D);
		
		model.addAttribute("page", items);
		model.addAttribute("mainItems", mainItems);
		
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
