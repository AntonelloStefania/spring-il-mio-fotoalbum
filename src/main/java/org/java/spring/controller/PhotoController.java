package org.java.spring.controller;

import java.util.List;

import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PhotoController {

	@Autowired
	PhotoService photoService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public String getPhotos(Model model,
			@RequestParam(required = false) String search) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
	        if (authentication != null && authentication.getPrincipal() instanceof User) {
	            User user = (User) authentication.getPrincipal();
	            int userId = user.getId();
	           
	            if (user.isAdmin()) {
	            	List<Photo> photos = search == null 
						? photoService.findByUserId(userId)
						: photoService.findByName(search, userId);
				model.addAttribute("photos", photos);
				
		            }else if(user.isSuperAdmin()) {
		            	List<Photo> photos = search == null 
								? photoService.findAll()
								: photoService.findAllByName(search);
						model.addAttribute("photos", photos);
		            }
	       
	            model.addAttribute("search", search == null ? "" : search);
	            model.addAttribute("userId", userId);
	        }
		 
		return "firstPage";
	}
	
	@GetMapping("/photo/{id}")
	public String showPhoto(Model model,
				@PathVariable int id) {
		Photo photo = photoService.findById(id);
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("photo", photo);
		
		return "showPhoto";
	}
	
	@GetMapping("/photo/create")
	public String createPhoto(Model model) {
		
        Photo photo = new Photo();
        
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photoForm";
	}
	
	@PostMapping("photo/create")
	public String storePhoto(Model model,
				@Valid @ModelAttribute Photo photo,
				BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
           
		photo.setUser(user);
		List<Category> categories = categoryService.findAll();
		if(bindingResult.hasErrors()) {
			model.addAttribute("photo", photo);
			model.addAttribute("categories", categories);
			return "photoForm";
		}
		photoService.save(photo);
	}
		return "redirect:/";
	}
	
	@GetMapping("/photo/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
		Photo photo = photoService.findById(id);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("photo",photo);
		
		return "photoForm";
	}
	
	@PostMapping("/photo/edit/{id}")
	public String updatePhoto(Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult
			) {
		return storePhoto(model, photo, bindingResult);
	}
	
	@PostMapping("/photo/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Photo photo = photoService.findById(id);		
		photoService.delete(photo);
		
		return "redirect:/";
	}
	
}
