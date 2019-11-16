package com.student.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Student implements Serializable
{
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Size(min = 2,max = 10,message = "please enter the name")
	private String name;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dateBirth;
	@NotEmpty
	@Email
	private String email;
	private String photo;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, Date dateBirth, String email, String photo) {
		super();
		this.name = name;
		this.dateBirth = dateBirth;
		this.email = email;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	
	

}
