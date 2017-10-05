package org.juon.jpashop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.juon.jpashop.enums.DeliveryStatus;

@Entity
public class Delivery {
	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	public Delivery() {}
	
	public Delivery(Address address) {
		this.address = address;
		this.deliveryStatus = DeliveryStatus.READY;
	}
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DELIVERY_STATUS")
	private DeliveryStatus deliveryStatus;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", order=" + order + ", address=" + address + ", deliveryStatus=" + deliveryStatus
				+ ", deliveryDate=" + deliveryDate + "]";
	}
}
