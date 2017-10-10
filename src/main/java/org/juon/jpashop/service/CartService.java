package org.juon.jpashop.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	public void save(Item item, int quantity, Member member) {
		Cart cart = Cart.createCart(member, item, item.getPrice(), quantity);
		cartRepository.save(cart);
	}
	
	public Page<Cart> getCartList(Member member, Pageable pageable) {
		return cartRepository.findByMember(member, pageable);
	}
	
	public List<Cart> getCartListByMember(Member member) {
		return cartRepository.findByMember(member).collect(toList());
	}
	
	public void deleteCart(Long id) {
		Cart cart = cartRepository.findOne(id);
		cart.cancelCart();
		cartRepository.delete(cart);
	}
}
