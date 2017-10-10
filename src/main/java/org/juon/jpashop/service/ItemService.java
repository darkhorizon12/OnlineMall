package org.juon.jpashop.service;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.ItemRepository;
import org.juon.jpashop.repository.ItemRepositoryImpl;
import org.juon.jpashop.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ItemRepositoryImpl itemRepositoryImpl;
	
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public void saveCategoryItem(CategoryItem categoryItem) {
		itemRepository.saveCategoryItem(categoryItem);
	}
	
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	
	public List<Item> findAll(Pagination pagination) {
		return itemRepository.findAll(pagination);
	}
	
	public void delteItem(Long id) {
		Item item = findOne(id);
		itemRepository.deleteItem(item);
		
		deleteFile(item);
	}
	
	public void deleteFile(Item item) {
		/** DELETE FILE **/
		String filename = item.getFilename();
		if (filename != null) {
			File file = new File(filename);
			if (file.exists()) {
				FileSystemUtils.deleteRecursively(file);
			}
		}
	}
	
	public List<Item> findAllItems() {
		Stream<Item> itemStreams = itemRepositoryImpl.findAllItems();
		List<Item> items = 
				itemStreams.filter(item -> item.getPrice() > 20_000).collect(toList());
		
		return items;
	}
	
	public List<Item> fifindMoreExpensiveThanPrice(Double price) {
		Map<String, Double> param = new HashMap<>();
		param.put("price", price);
		Stream<Item> stream = itemRepositoryImpl.findMoreExpensiveThanPrice(price);
		return stream.collect(toList());
	}
}
