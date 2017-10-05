package org.juon.jpashop.web;

import org.juon.jpashop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupons")
public class CouponController {
	@Autowired
	CouponService couponService;
	
	@GetMapping(value = "/masters/new")
	public String createCouponMasterForm() {
		return "coupons/createCouponMasterForm";
	}
}
