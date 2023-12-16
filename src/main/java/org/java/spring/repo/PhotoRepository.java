package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	List<Photo> findByNameContainingIgnoreCaseAndUser_id(String name, int id);
	List<Photo> findByUserId(int id);
	List<Photo> findByVisibleTrue();
	
}
