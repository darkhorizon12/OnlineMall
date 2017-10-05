package org.juon.jpashop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.enums.CouponDiscountType;
import org.juon.jpashop.enums.CouponPublicationType;

@Entity
public class CouponMaster {
	@Id @GeneratedValue
	@Column(name = "COUPON_MASTER_ID")
	private Long id;
	
	private String name;
	private String startDate;
	private String endDate;
	private int couponCount;
	
	@Enumerated(EnumType.STRING)
	private CouponPublicationType publicationType;
	
	@Enumerated(EnumType.STRING)
	private CouponDiscountType discountType;
	
	private double discountPrice;
	private double minPrice;
	private double limitPrice;
	private boolean dupFlag;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	@OneToMany(mappedBy = "couponMaster")
	private List<Coupon> coupons = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			name = "CouponCategory", 
			joinColumns = @JoinColumn(name = "COUPON_MASTER_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
	private List<Category> couponCategories = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			name = "CouponItem",
			joinColumns = @JoinColumn(name = "COUPON_MASTER_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private List<Item> couponItems = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public CouponPublicationType getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(CouponPublicationType publicationType) {
		this.publicationType = publicationType;
	}

	public CouponDiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(CouponDiscountType discountType) {
		this.discountType = discountType;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public boolean isDupFlag() {
		return dupFlag;
	}

	public void setDupFlag(boolean dupFlag) {
		this.dupFlag = dupFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public List<Category> getCouponCategories() {
		return couponCategories;
	}

	public void setCouponCategories(List<Category> couponCategories) {
		this.couponCategories = couponCategories;
	}

	public List<Item> getCouponItems() {
		return couponItems;
	}

	public void setCouponItems(List<Item> couponItems) {
		this.couponItems = couponItems;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("CouponMaster [id=").append(id)
				.append(", name=").append(name)
				.append(", startDate=").append(startDate)
				.append(", endDate=").append(endDate)
				.append(", couponCount=").append(couponCount)
				.append(", publicationType=").append(publicationType)
				.append(", discountType=").append(discountType)
				.append(", discountPrice=").append(discountPrice)
				.append(", minPrice=").append(minPrice)
				.append(", limitPrice=").append(limitPrice)
				.append(", dupFlag=").append(dupFlag)
				.append(", createDate=").append(createDate)
				.append(", coupons=").append(coupons)
				.append(", couponCategories=").append(couponCategories)
				.append(", couponItems=").append(couponItems)
				.append("]").toString();
	}
	
	
	
	
}
