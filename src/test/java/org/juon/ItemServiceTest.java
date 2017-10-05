package org.juon;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	public void test() {
	}

}
