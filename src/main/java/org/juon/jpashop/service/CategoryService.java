package org.juon.jpashop.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.juon.jpashop.domain.Category;
import org.juon.jpashop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public List<Category> findParent() {
		return categoryRepository.findParent().collect(toList());
	}
	
	public List<Category> findChildren(Long id) {
		return categoryRepository.findChildren(id).collect(toList());
	}
}
