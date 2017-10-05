package org.juon.jpashop.service;

import java.io.File;
import java.util.List;

import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.ItemRepository;
import org.juon.jpashop.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
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
}
