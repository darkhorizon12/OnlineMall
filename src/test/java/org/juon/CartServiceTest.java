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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {
	@Autowired MemberRepository memberRepository;
	@Autowired CartRepository cartRepository;
	@Autowired ItemRepository itemRepository;
	@Autowired CartService cartService;
	final String username = "member1@123.com";
/*	@Test
	public void test() {
		Member member = memberRepository.findByEmail(username);
		Item item1 = itemRepository.findOne(215L);
		Item item2 = itemRepository.findOne(218L);
		Item item3 = itemRepository.findOne(221L);
		
		Cart cart1 = Cart.createCart(member, item1, item1.getPrice(), 70);
		Cart cart2 = Cart.createCart(member, item2, item2.getPrice(), 3);
		Cart cart3 = Cart.createCart(member, item3, item3.getPrice(), 3);
		
		cart1 = cartRepository.save(cart1);
		cart2 = cartRepository.save(cart2);
		cart3 = cartRepository.save(cart3);
		
		assertEquals(100, item1.getStockQuantity());
		cartService.deleteCart(cart1.getId());
		assertEquals(170, item1.getStockQuantity());
		
		long count = cartRepository.findByMember(member).count();
		assertEquals(2, count);
	}*/
	
	@Test
	public void listTest() {
		PageRequest request = new PageRequest(0, 2);
		Member member = memberRepository.findByEmail(username);
		Page<Cart> pageList = cartService.getCartList(member, request);
		
		System.out.println("!@!@!!@!@@!@@!@!@@!@!@!@!@!@!@!@!");
		System.out.println("request.getPageSize() =====>>>> [" + request.getPageSize() + "]");
		System.out.println("pageList.getNumber() ======>>>> [" + pageList.getNumber() + "]");
		System.out.println("pageList.getContent().size() ======>>>> [" + pageList.getContent().size() + "]");
		System.out.println("pageList.getNumberOfElements() ======>>>> [" + pageList.getNumberOfElements() + "]");
		System.out.println("pageList.getSize() ======>>>> [" + pageList.getSize() + "]");
		System.out.println("pageList.getTotalElements() ======>>>> [" + pageList.getTotalElements() + "]");
		System.out.println("pageList.getTotalPages() ======>>>> [" + pageList.getTotalPages() + "]");
		System.out.println("pageList.hasNext() ======>>>> [" + pageList.hasNext() + "]");
		System.out.println("pageList.hasPrevious() ======>>>> [" + pageList.hasPrevious() + "]");
		System.out.println("pageList.isFirst() ======>>>> [" + pageList.isFirst() + "]");
		System.out.println("pageList.isLast() ======>>>> [" + pageList.isLast() + "]");
		System.out.println("!@!@!!@!@@!@@!@!@@!@!@!@!@!@!@!@!");
		
	}

}
