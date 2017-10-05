package org.juon.jpashop.service;

import java.util.List;

import org.juon.jpashop.addtional.OrderSearch;
import org.juon.jpashop.domain.Delivery;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.Order;
import org.juon.jpashop.domain.OrderItem;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.MemberRepository;
import org.juon.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired MemberRepository memberRepository;
	@Autowired OrderRepository orderRepository;
	@Autowired ItemService itemService;
	
	public Long order(Long memberId, Long itemId, int quantity) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemService.findOne(itemId);
		
		Delivery delivery = new Delivery(member.getAddress());
		
		OrderItem orderItem = 
				OrderItem.createOrderItem(item, item.getPrice(), quantity);
		
		Order order = Order.createOrder(member, delivery, orderItem);
		
		orderRepository.save(order);
		
		return order.getId();
	}
	
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		
		order.cancelOrder();
	}
	
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}
	
}
