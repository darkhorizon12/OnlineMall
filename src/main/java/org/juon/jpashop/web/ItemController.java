package org.juon.jpashop.web;

import java.util.ArrayList;
import java.util.List;

import org.juon.jpashop.domain.Category;
import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.domain.item.Book;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.service.CategoryService;
import org.juon.jpashop.service.ItemService;
import org.juon.jpashop.service.StorageService;
import org.juon.json.CategoryJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/items")
public class ItemController extends BasicController{
	@Autowired 
	ItemService itemService;
	@Autowired 
	CategoryService categoryService;
	@Autowired 
	StorageService storageServcie;
	
	@RequestMapping("")
	public String list(Model model, Integer page) {
		Pageable pageable = createPage(page);
		final Page<Item> items = itemService.findAll(pageable);
		
		model.addAttribute("page", items);
		model.addAttribute("pagination", createPagination(items));
		
		return "items/itemList";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<CategoryJson>> getCategories(@RequestParam("categoryId") String categoryId) {
		List<Category> categoryList = categoryService.findChildren(Long.valueOf(categoryId));
		List<CategoryJson> list = new ArrayList<CategoryJson>();
		for (Category c : categoryList) {
			CategoryJson j = new CategoryJson();
			j.setId(c.getId());
			j.setName(c.getName());
			
			list.add(j);
		}
		
		return new ResponseEntity<List<CategoryJson>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createForm(Model model) {
		List<Category> parents = categoryService.findParent();
		model.addAttribute("categories", parents);
		
		return "items/createItemForm";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(Book item, 
			@RequestParam("category1") String category1, @RequestParam("category2") String category2,
			@RequestParam("file") MultipartFile file) {
		item.setOrgFilename(file.isEmpty() ? null : file.getOriginalFilename());
		item.setFilename(file.isEmpty() ? null : storageServcie.store(file));
		
		if (StringUtils.hasText(category1)) {
			CategoryItem categoryItem = new CategoryItem();
			categoryItem.setItem(item);
			categoryItem.setCategory(categoryService.findOne(Long.valueOf(category1)));
			item.getCategoryItems().add(categoryItem);
		}
		
		if (StringUtils.hasText(category2)) {
			CategoryItem categoryItem = new CategoryItem();
			categoryItem.setItem(item);
			categoryItem.setCategory(categoryService.findOne(Long.valueOf(category2)));
			item.getCategoryItems().add(categoryItem);
		}

		itemService.saveItem(item);
		
		
		return "redirect:/items";
	}
	
	@RequestMapping(value = "/{itemId}/edit", method = RequestMethod.GET)
	public String editForm(@PathVariable("itemId") Long itemId, Model model) {
		
		Item item = itemService.findOne(itemId);
		model.addAttribute("item", item);
		
		return "items/updateItemForm";
	}
	
	@RequestMapping(value = "/{itemId}/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("item") Book item, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			itemService.deleteFile(item);
			item.setOrgFilename(file.isEmpty() ? null : file.getOriginalFilename());
			item.setFilename(file.isEmpty() ? null : storageServcie.store(file));
		}
		itemService.saveItem(item);
		
		return "redirect:/items";
	}
	
	@GetMapping(value = "/{itemId}/view")
	public String view(@PathVariable("itemId") Long itemId, Model model) {
		
		Item item = itemService.findOne(itemId);
		model.addAttribute("item", item);
		
		return "items/viewItem";
	}
	
	@GetMapping(value = "/{itemId}/del")
	public String delete(@PathVariable("itemId") Long itemId) {
		itemService.delteItem(itemId);
		
		return "redirect:/items";
	}
}
