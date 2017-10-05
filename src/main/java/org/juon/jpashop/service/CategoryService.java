package org.juon.jpashop.service;

import java.util.List;

import org.juon.jpashop.domain.Category;
import org.juon.jpashop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
		return categoryRepository.findParent();
	}
	
	public List<Category> findChildren(Long id) {
		return categoryRepository.findChildren(id);
	}
}
