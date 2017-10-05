package org.juon;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.CartRepository;
import org.juon.jpashop.repository.ItemRepository;
import org.juon.jpashop.repository.MemberRepository;
import org.juon.jpashop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {
	@Autowired MemberRepository memberRepository;
	@Autowired CartRepository cartRepository;
	@Autowired ItemRepository itemRepository;
	@Autowired CartService cartServcie;
	
	@Test
	public void test() {
		String username = "member1@123.com";
		Member member = memberRepository.findByEmail(username);
		Item item1 = itemRepository.findOne(215L);
		Item item2 = itemRepository.findOne(218L);
		Item item3 = itemRepository.findOne(221L);
		
		Cart cart1 = Cart.createCart(member, item1, item1.getPrice(), 70);
		Cart cart2 = Cart.createCart(member, item2, item1.getPrice(), 3);
		Cart cart3 = Cart.createCart(member, item3, item1.getPrice(), 3);
		
		cartRepository.save(cart1);
		cartRepository.save(cart2);
		cartRepository.save(cart3);
		
		assertEquals(100, item1.getStockQuantity());
		cartServcie.deleteCart(cart1.getId());
		assertEquals(170, item1.getStockQuantity());
		
		long count = cartRepository.findAllByMember(member)
						.stream()
						.count();
		assertEquals(2, count);
	}

}
