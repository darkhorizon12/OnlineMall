package org.juon.jpashop.service;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	
	public Page<Item> findAll(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}
	
	public void delteItem(Long id) {
		Item item = findOne(id);
		itemRepository.delete(item);
		
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
	
	public List<Item> fifindMoreExpensiveThanPrice(Double price) {
		Map<String, Double> param = new HashMap<>();
		param.put("price", price);
		Stream<Item> stream = itemRepository.findMoreExpensiveThanPrice(price);
		return stream.collect(toList());
	}
}
