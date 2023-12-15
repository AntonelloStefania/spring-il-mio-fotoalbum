package org.java.spring.service;

import java.util.List;

import org.java.spring.pojo.Message;
import org.java.spring.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepository;
	
	public List<Message> findAll(){
		return messageRepository.findAll();
	}
	public Message findById(int id) {
		return messageRepository.findById(id).get();
	}
	public void save(Message message) {
		messageRepository.save(message);
	}
	public void delete(Message message) {
		messageRepository.delete(message);
	}
}
