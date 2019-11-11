package com.student.dao;

import java.util.Date;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>
{
   
	public List<Student> findByName(String name);
	public Page<Student> findByName(String name,Pageable pageable);
	
	@Query("select s from Student s where s.name like :x ")
	public Page<Student> searchStudent(@Param("x")String mc,Pageable pageable);
	
	
	@Query("select s from Student s where s.dateBirth >:x and s.dateBirth <:y ")
	public List<Student> searchStudent(@Param("x")Date d1,@Param("y")Date d2);
	
	
}
