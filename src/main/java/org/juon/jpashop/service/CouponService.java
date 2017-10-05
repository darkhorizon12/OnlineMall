package org.juon.jpashop.service;

import org.juon.jpashop.domain.Coupon;
import org.juon.jpashop.domain.CouponMaster;
import org.juon.jpashop.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	@Autowired 
	CouponRepository couponRepository;
	
	public Long saveCouponMaster(CouponMaster couponMaster) {
		return couponRepository.saveCouponMaster(couponMaster);
	}
	
	public Long saveCoupon(Coupon coupon) {
		return couponRepository.saveCoupon(coupon);
	}
	
	public CouponMaster findCouponMaster(Long id) {
		return couponRepository.findCouponMaster(id);
	}

	public Coupon findCoupon(Long id) {
		return couponRepository.findCoupon(id);
	}
}
