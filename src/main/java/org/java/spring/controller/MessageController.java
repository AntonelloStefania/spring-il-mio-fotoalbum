package org.java.spring.controller;

import java.util.List;

import org.java.spring.pojo.Message;
import org.java.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

	@Autowired
	MessageService messageService;
	
	
	
	@GetMapping("/messages")
	public String getMessage(Model model) {
		List<Message> messages = messageService.findAll();	
		model.addAttribute("messages", messages);
		
		return "/messages";
	}
	
	@PostMapping("/back-home")
	public String updateMessages(Model model) {
	    List<Message> messages = messageService.findAll();
	    
	    for (Message message : messages) {
	        message.setReaded(true);
	       
	        messageService.save(message);
	    }

	    
	    return "redirect:/";
	}
}
