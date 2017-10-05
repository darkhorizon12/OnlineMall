package org.juon.jpashop.web;

import java.util.List;

import org.juon.jpashop.domain.Category;
import org.juon.jpashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired CategoryService categoryService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<Category> categories = categoryService.findParent();
		model.addAttribute("categories", categories);
		
		return "/categories/categoryList";
	}
	
	@RequestMapping(value = "/{parentId}/list")
	public String childrenList(@PathVariable("parentId") Long parentId, Model model) {
		
		List<Category> categories = categoryService.findChildren(parentId);
		model.addAttribute("categories", categories);
		
		return "/categories/categoryList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createForm(Model model) {
		List<Category> categories = categoryService.findParent();
		model.addAttribute("categories", categories);
		
		return "/categories/createCategoryForm";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@RequestParam("name") String name, @RequestParam("parent") Long parentId) {
		Category category = new Category();
		category.setName(name);
		if (parentId != null) {
			category.setParent(categoryService.findOne(parentId));
		}
		categoryService.save(category);
		
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "/{categoryId}/edit", method = RequestMethod.GET)
	public String editForm(@PathVariable("categoryId") Long id, Model model) {
		List<Category> categories = categoryService.findParent();
		model.addAttribute("categories", categories);
		
		Category category = categoryService.findOne(id);
		model.addAttribute("category", category);
		
		
		return "/categories/editCategoryForm";
	}
}
