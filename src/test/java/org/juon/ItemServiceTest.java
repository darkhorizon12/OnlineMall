package org.juon;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired ItemService itemService;
	
	
	@Test
	public void findAllTest() {
		List<Item> items= itemService.findAllItems();
		
		assertEquals(2, items.size());
	}
	
	@Test
	public void findMoreExpensiveThanPrice() {
		List<Item> items = itemService.fifindMoreExpensiveThanPrice(20_000D);
		System.out.println(items);
		assertEquals(2, items.size());
	}

}
