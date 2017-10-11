package org.juon;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Category;
import org.juon.jpashop.domain.Coupon;
import org.juon.jpashop.domain.CouponMaster;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.enums.CouponDiscountType;
import org.juon.jpashop.enums.CouponPublicationType;
import org.juon.jpashop.service.CategoryService;
import org.juon.jpashop.service.CouponService;
import org.juon.jpashop.service.ItemService;
import org.juon.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CouponServiceTest {
	@Autowired CategoryService categoryService;
	@Autowired ItemService itemService;
	@Autowired CouponService couponService;
	@Autowired MemberService memberService;
	
	@Test
	public void save() {
		/* Create CouponMaster
		 * Set CouponCategory or CouponItem
		 * 
		 * Create Coupon
		 * 
		 * Check couponMaster.getCoupons()
		 */
		CouponMaster master = new CouponMaster();
		master.setName("CELEBRATE JOIN");
		master.setStartDate("20171001");
		master.setEndDate("20171031");
		master.setCouponCount(100);
		
		master.setPublicationType(CouponPublicationType.CATEGORY);
		master.setDiscountType(CouponDiscountType.PERCENTAGE);
		master.setDiscountPrice(0.1);
		master.setMinPrice(10_000);
		master.setLimitPrice(0);
		master.setDupFlag(true);
		
		master.setCreateDate(new Date());
		
		Category c1 = categoryService.findOne(57L);
		master.getCouponCategories().add(c1);
		
		CouponMaster getMaster = couponService.saveCouponMaster(master);
		
		CouponMaster savedMaster = couponService.findCouponMaster(getMaster.getId());
		
		assertEquals(master, savedMaster);
		assertEquals(c1, savedMaster.getCouponCategories().get(0));
		
		Member m = memberService.findByEmail("member1@12.com");
		Coupon coupon1 = new Coupon();
		coupon1.setMember(m);
		coupon1.setCouponMaster(master);
		coupon1.setDelFlag(false);
		coupon1.setCreateDate(new Date());
		coupon1.setUseFlag(false);
		
		master.getCoupons().add(coupon1);
		couponService.saveCoupon(coupon1);

		Coupon coupon2 = new Coupon();
		coupon2.setCouponMaster(master);
		coupon2.setDelFlag(false);
		coupon2.setCreateDate(new Date());
		coupon2.setUseFlag(false);
		
		master.getCoupons().add(coupon2);
		couponService.saveCoupon(coupon2);

		Coupon coupon3 = new Coupon();
		coupon3.setCouponMaster(master);
		coupon3.setDelFlag(false);
		coupon3.setCreateDate(new Date());
		coupon3.setUseFlag(false);
		
		master.getCoupons().add(coupon3);
		couponService.saveCoupon(coupon3);
		
		assertEquals(3, getMaster.getCoupons().size());
		assertEquals(3, coupon1.getCouponMaster().getCoupons().size());
		
		
	}

}
