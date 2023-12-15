package org.java.spring.controller;

import java.util.List;

import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	 @Autowired
	 PhotoService photoService;
	
	@GetMapping("/categories")
	public String getCategories(Model model) {
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "categoryIndex";
	}
	
	@GetMapping("/categories/create")
	public String createCategory(Model model) {
		Category category = new Category();
		
		model.addAttribute("category", category);
		
		return "categoryForm";
	}
	
	@PostMapping("/categories/create")
	public String storeCategory(Model model,
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult
				) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			return "categoryForm";
		}
		
		categoryService.save(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit/{id}")
	public String editCategory(Model model, @PathVariable int id) {
		Category category = categoryService.findById(id);
		
		model.addAttribute("category", category);
		
		return "categoryForm";
	}
	@PostMapping("/categories/edit/{id}")
	public String updateCategory(Model model,
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult
			) {
		return storeCategory(model, category, bindingResult);
	}
	
	@PostMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		Category category = categoryService.findById(id);
		   List<Photo> categoriesPhotos = category.getPhotos();
	        
	        categoriesPhotos.forEach(photo -> {
	            photo.getCategories().remove(category);
	            photoService.save(photo);
	        });
	        
		categoryService.delete(category);
		
		return "redirect:/categories";
	}
	
}
