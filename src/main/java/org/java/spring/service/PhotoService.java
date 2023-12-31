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
	
	public List<Photo> findByName(String query, int id){
		return photoRepository.findByNameContainingIgnoreCaseAndUser_id(query, id);
	}
	public List<Photo> findAllByName(String query){
		return photoRepository.findByNameContainingIgnoreCase(query);
	}
	
	public List<Photo> findByVisible(){
		return photoRepository.findByVisibleFalse();
	}
	public List<Photo> findByUserId(int id){
		return photoRepository.findByUserId(id);
	}
	
	public void save(Photo photo) {
		photoRepository.save(photo);
	}
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}
}
