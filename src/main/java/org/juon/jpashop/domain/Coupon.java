package org.juon.jpashop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Coupon {
	@Id @GeneratedValue
	@Column(name = "COUPON_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUPON_MASTER_ID")
	private CouponMaster couponMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	private boolean useFlag;
	private boolean delFlag;
		
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date useDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CouponMaster getCouponMaster() {
		return couponMaster;
	}

	public void setCouponMaster(CouponMaster couponMaster) {
		this.couponMaster = couponMaster;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public boolean isUseFlag() {
		return useFlag;
	}

	public void setUseFlag(boolean useFlag) {
		this.useFlag = useFlag;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Coupon [id=").append(id)
				.append(", couponMaster=").append(couponMaster)
				.append(", member=").append(member)
				.append(", useFlag=").append(useFlag)
				.append(", delFlag=").append(delFlag)
				.append(", useDate=").append(useDate)
				.append(", createDate=").append(createDate)
				.append("]").toString();
	}
	
	
}
