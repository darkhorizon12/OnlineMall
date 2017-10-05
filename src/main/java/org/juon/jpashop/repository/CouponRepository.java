package org.juon.jpashop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.juon.jpashop.domain.Coupon;
import org.juon.jpashop.domain.CouponMaster;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {
	@PersistenceContext EntityManager em;
	
	public Long saveCouponMaster(CouponMaster couponMaster) {
		em.persist(couponMaster);
		
		return couponMaster.getId();
	}

	public Long saveCoupon(Coupon coupon) {
		em.persist(coupon);
		
		return coupon.getId();
	}
	
	public CouponMaster findCouponMaster(Long id) {
		return em.find(CouponMaster.class, id);
	}
	
	public Coupon findCoupon(Long id) {
		return em.find(Coupon.class, id);
	}
}
