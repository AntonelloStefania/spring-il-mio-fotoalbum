package org.java.spring.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.java.spring.auth.db.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 32, nullable=false)
	@Length(min=4, max=32, message="il nome deve essere di minimo 4 caratteri e massimo 32")
	@NotBlank(message="il nome non può contenere spazi vuoti")
	private String name;
	
	@Column(columnDefinition = "TEXT", length=500)
	@Length(max=500, message="la descrizione può essere di massimo 500 caratteri")
	@NotBlank(message="la descrizione non può contenere spazi vuoti")
	 private String description;
	
	@Column(columnDefinition = "TEXT", nullable=false)
	@URL(protocol="https")
	@NotBlank(message="la foto URL non può contenere spazi vuoti")
	private String url;

	
	@Column(nullable=false)
	boolean visible;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	@Column(nullable=false)
	@Size(min = 1, message = "Devi selezionare almeno una categoria")
	private List<Category> categories;
	
	public Photo() {};
	public Photo(String name, String description, String url, boolean visible, User user,Category...categories) {
		setName(name);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setUser(user);
		setCategories(categories);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void setCategories(Category...categories) {
		setCategories(Arrays.asList(categories));
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
