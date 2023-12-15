package org.java.spring.db.restcontroller;

import org.java.spring.pojo.Message;
import org.java.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class MessageRestcontroller {
	@Autowired
	MessageService messageService;
	
	@PostMapping
	public ResponseEntity<Message> create(@RequestBody Message message){
		messageService.save(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
}
