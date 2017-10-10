package org.juon.jpashop.addtional;

import static org.juon.jpashop.domain.OrderSpec.memberNameLike;
import static org.juon.jpashop.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specifications.where;

import org.juon.jpashop.domain.Order;
import org.juon.jpashop.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class OrderSearch {
	
	private String memberName;
	private OrderStatus orderStatus;

	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Specification<Order> toSpecification() {
		return where(memberNameLike(memberName)).and(orderStatusEq(orderStatus));
	}			
}
