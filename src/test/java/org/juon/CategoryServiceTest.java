package org.juon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Category;
import org.juon.jpashop.repository.CategoryRepository;
import org.juon.jpashop.repository.ItemRepository;
import org.juon.jpashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class CategoryServiceTest {

	@Autowired CategoryService service;
	@Autowired CategoryRepository repository;
	@Autowired ItemRepository itemRepository;
	
	@Test
	public void save() {
		//Given
		
		Category parent = new Category();
		parent.setName("parent1");
		service.save(parent);
		
		Category child1 = new Category();
		child1.setName("child1");
		child1.setParent(parent);
		service.save(child1);
		
		Category child2 = new Category();
		child2.setName("child2");
		child2.setParent(parent);
		service.save(child2);
		
		Category child3 = new Category();
		child3.setName("child2");
		child3.setParent(parent);
		service.save(child3);
		
		child3.setParent(null);
		
		
		/*
		parent.addChild(child1);
		parent.addChild(child2);
		*/
		//When
		Category getCategory = service.findOne(child1.getId());
		assertEquals("부모의 카테고리의 이름은 parent1.", "parent1", getCategory.getParent().getName());
		Category getParent = service.findOne(parent.getId());
		assertEquals("자식 카테고리 수는 2.", 2, getParent.getChildren().size());
	}
	
	@Test
	public void findChildren() {
		//Given
		
		Category parent = new Category();
		parent.setName("parent1");
		service.save(parent);
		
		Category child1 = new Category();
		child1.setName("child1");
		child1.setParent(parent);
		service.save(child1);
		
		Category child2 = new Category();
		child2.setName("child2");
		child2.setParent(parent);
		service.save(child2);
		
		//When
		Category p = service.findOne(parent.getId());
		assertEquals("자식 카테고리 수는 2.", 2, p.getChildren().size());		
	}

}
