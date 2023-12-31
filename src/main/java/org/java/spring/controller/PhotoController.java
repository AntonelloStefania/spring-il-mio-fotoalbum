package org.java.spring.controller;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Message;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.MessageService;
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
	
	@Autowired
	MessageService messageService;
	
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
		            	List<Message> messages = messageService.findAll();
		            	model.addAttribute("messages", messages);
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
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
        User user = (User) authentication.getPrincipal();
		Photo photo = photoService.findById(id);
					
			if(photo.getUser().getId() == user.getId() || user.isSuperAdmin()) {
				List<Category> categories = categoryService.findAll();            
				model.addAttribute("photo", photo);            
				model.addAttribute("categories", categories);        
				
				return "showPhoto";
			}
			else {
				return "redirect:/";
			}
		
		
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
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
	        User user = (User) authentication.getPrincipal();
	
		Photo photo = photoService.findById(id);
		if(photo.getUser().getId() == user.getId() || user.isSuperAdmin()) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("photo",photo);
		
		return "photoForm";
		}else {
			return "redirect:/";
		}
	}
	
	
	 @PostMapping("photo/edit/{id}")
	    public String updatePhoto(Model model, @Valid @ModelAttribute Photo updatedPhoto, BindingResult bindingResult, Authentication authentication) {
	        if (bindingResult.hasErrors()) {
	            // Handle validation errors
	            model.addAttribute("photo", updatedPhoto);
	            return "redirect:/";
	        }

	        if (authentication != null && authentication.getPrincipal() instanceof User) {
	            User user = (User) authentication.getPrincipal();
	            
	            Photo photo = photoService.findById(updatedPhoto.getId());

	            if (user.isSuperAdmin()) {
	                photo.setVisible(updatedPhoto.isVisible());
	                photo.setChecked(updatedPhoto.isChecked());
	                if (photo.isChecked()) {
	                    photo.setVisible(true);
	                }
	            } else {
	                photo.setName(updatedPhoto.getName());
	                photo.setDescription(updatedPhoto.getDescription());
	                photo.setUrl(updatedPhoto.getUrl());
	                photo.setCategories(updatedPhoto.getCategories());
	                photo.setVisible(updatedPhoto.isVisible());
	                
	            }

	            photoService.save(photo);
	        }

	        return "redirect:/";
	    }
	@PostMapping("/photo/delete/{id}")
	public String deletePhoto(@PathVariable int id) {
		Photo photo = photoService.findById(id);		
		photoService.delete(photo);
		
		return "redirect:/";
	}
	

	
}
