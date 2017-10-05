package org.juon;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Category;
import org.juon.jpashop.domain.CouponMaster;
import org.juon.jpashop.enums.CouponDiscountType;
import org.juon.jpashop.enums.CouponPublicationType;
import org.juon.jpashop.service.CategoryService;
import org.juon.jpashop.service.CouponService;
import org.juon.jpashop.service.ItemService;
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
		
		Long masterId = couponService.saveCouponMaster(master);
		
		CouponMaster savedMaster = couponService.findCouponMaster(masterId);
		
		assertEquals(master, savedMaster);
		assertEquals(c1, savedMaster.getCouponCategories().get(0));
		
		
	}

}
