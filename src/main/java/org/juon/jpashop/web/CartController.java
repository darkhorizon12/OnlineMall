package org.juon.jpashop.web;

import java.security.Principal;

import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.service.CartService;
import org.juon.jpashop.service.ItemService;
import org.juon.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/carts")
public class CartController extends BasicController{
	@Autowired
	CartService cartService;
	@Autowired
	MemberService memberService;
	@Autowired
	ItemService itemService;
	
	@GetMapping("")
	public String list(Principal principal, Model model, Integer page) {
		final String username = principal.getName();
		final Member member = memberService.findByEmail(username);
		
		Pageable pageable = createPage(page);
		
		final Page<Cart> carts = cartService.getCartList(member, pageable);
		
		double totalPrice = carts.getContent().stream().mapToDouble(cart -> cart.getQuantity() * cart.getOrderPrice()).sum();
		model.addAttribute("page", carts);
		model.addAttribute("pagenation", createPagination(carts));
		model.addAttribute("totalPrice", totalPrice);
		
		return "/carts/cartList";
	}
	
	@PostMapping(value = "/{itemId}/add")
	public String addCart(@PathVariable("itemId") Long id, @RequestParam("selectedQuantity") int quantity, Principal principal) {
		Member member = memberService.findByEmail(principal.getName());
		Item item = itemService.findOne(id);
		cartService.save(item, quantity, member);
		
		return "redirect:/carts";
	}

	@GetMapping(value = "/{itemId}/del")
	public String addCart(@PathVariable("itemId") Long id) {
		cartService.deleteCart(id);		
		return "redirect:/carts";
	}
}
