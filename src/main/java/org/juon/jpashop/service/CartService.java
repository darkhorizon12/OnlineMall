package org.juon.jpashop.service;

import java.util.List;

import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	public void save(Item item, int quantity, Member member) {
		Cart cart = Cart.createCart(member, item, item.getPrice(), quantity);
		cartRepository.save(cart);
	}
	
	public List<Cart> getCartList(Member member) {
		return cartRepository.findAllByMember(member);
	}
	
	public void deleteCart(Long id) {
		Cart cart = cartRepository.findOne(id);
		cart.cancelCart();
		cartRepository.remove(cart);
	}
}
