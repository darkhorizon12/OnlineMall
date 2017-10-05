package org.juon.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.juon.jpashop.domain.item.Item;

@Entity
public class Cart {
	
	@Id @GeneratedValue
	@Column(name = "CART_ID")
	private Long id;
	
	private double orderPrice;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	// -- Create Cart Method -- //
	public static Cart createCart(Member member, Item item, double orderPrice, int quantity) {
		Cart cart = new Cart();
		cart.setMember(member);
		cart.setItem(item);
		cart.setOrderPrice(orderPrice);
		cart.setQuantity(quantity);
		
		item.removeStock(quantity);
		
		return cart;
	}
	
	public void cancelCart() {
		getItem().addStock(quantity);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", orderPrice=" + orderPrice + ", quantity=" + quantity + ", member=" + member
				+ ", item=" + item + "]";
	}
}
