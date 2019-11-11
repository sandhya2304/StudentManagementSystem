package com.student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.student.dao.StudentRepository;
import com.student.model.Student;

@SpringBootApplication
public class StudentSpringBootApplication 
{
	public static void main(String[] args) throws ParseException
	{
		
	    ApplicationContext context	= SpringApplication.run(StudentSpringBootApplication.class, args);
	    StudentRepository studentRepository = context.getBean(StudentRepository.class);
	
	    DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	    
		/*
		 * studentRepository.save(new
		 * Student("ram",df.parse("1941-4-23"),"ram@gmail.com","ramji.jpg"));
		 * studentRepository.save(new
		 * Student("harsh",df.parse("1939-11-1"),"harsh@gmail.com","harsh.jpg"));
		 * studentRepository.save(new
		 * Student("sandhya",df.parse("1942-3-9"),"sandy@gmail.com","sandhya.jpg"));
		 * studentRepository.save(new
		 * Student("mona",df.parse("1991-7-3"),"mona@gmail.com","mona.jpg"));
		 * studentRepository.save(new
		 * Student("maa",df.parse("1951-9-1"),"maa@gmail.com","maa.jpg"));
		 */
	    
	
	  //  System.out.println(studentRepository.toString());
	    
		/*
		 * Page<Student> std = studentRepository.findAll(new PageRequest(0, 5));
		 * std.forEach(e -> System.out.println(e.getName()));
		 */
	    
	    Page<Student> std = studentRepository.searchStudent("%a%", new PageRequest(0,5));
	    std.forEach(e -> System.out.println(e.getName()));
	    
	
	
	}

}
