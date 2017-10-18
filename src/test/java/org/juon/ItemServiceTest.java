package org.juon;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Category;
import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.domain.item.Book;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.service.CategoryService;
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
	@Autowired CategoryService categoryService;
	
	@Test
	public void saveCategoryItem() {
		Item item = new Book();
		item.setName("item1");
		item.setPrice(40_000D);
		item.setStockQuantity(20);
		
		
		List<Category> categories = categoryService.findParent();
		
		
		CategoryItem categoryItem = new CategoryItem();
		categoryItem.setItem(item);
		categoryItem.setCategory(categories.get(0));
		item.getCategoryItems().add(categoryItem);
		
		Item savedItem = itemService.saveItem(item);
		assertEquals(categoryItem, savedItem.getCategoryItems().get(0));
	}
	
	
	@Test
	public void findMoreExpensiveThanPrice() {
		List<Item> items = itemService.fifindMoreExpensiveThanPrice(20_000D);
		System.out.println(items);
		assertEquals(2, items.size());
	}

}
