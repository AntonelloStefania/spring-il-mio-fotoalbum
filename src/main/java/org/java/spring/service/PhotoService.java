package org.java.spring.service;

import java.util.List;

import org.java.spring.pojo.Photo;
import org.java.spring.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> findAll(){
		return photoRepository.findAll();
	}
	
	public Photo findById(int id) {
		return photoRepository.findById(id).get();
	}
	
	public List<Photo> findByName(String query){
		return photoRepository.findByNameContainingIgnoreCase(query);
	}
	
	public void save(Photo photo) {
		photoRepository.save(photo);
	}
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}
}
