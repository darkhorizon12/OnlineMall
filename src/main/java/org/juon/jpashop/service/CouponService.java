package org.juon.jpashop.service;

import org.juon.jpashop.domain.Coupon;
import org.juon.jpashop.domain.CouponMaster;
import org.juon.jpashop.repository.CouponMasterRepository;
import org.juon.jpashop.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	@Autowired 
	CouponRepository couponRepository;
	@Autowired
	CouponMasterRepository couponMasterRepository;
	
	public CouponMaster saveCouponMaster(CouponMaster couponMaster) {
		return couponMasterRepository.save(couponMaster);
	}
	
	public Coupon saveCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}
	
	public CouponMaster findCouponMaster(Long id) {
		return couponMasterRepository.findOne(id);
	}

	public Coupon findCoupon(Long id) {
		return couponRepository.findOne(id);
	}
}
