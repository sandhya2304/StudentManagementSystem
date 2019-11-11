package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.dao.StudentRepository;
import com.student.model.Student;

@Controller
@RequestMapping(value="/students")
public class StudentController
{
	@Autowired
	private StudentRepository studentRepository;
	
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String home(Model model,@RequestParam(name="page",defaultValue = "0")int p,
			@RequestParam(name="search",defaultValue = "")String search
			)
	{
		Page<Student> student = studentRepository.searchStudent("%"+search+"%",new PageRequest(p,5));
		int pageCount = student.getTotalPages();
		int[] pages = new int[pageCount];
		for(int i=0;i<pageCount;i++)
		  pages[i] = i;
		model.addAttribute("pages",pages);
		model.addAttribute("pageStudent",student);
		model.addAttribute("pageCurrent", p);
		model.addAttribute("search",search);
		return "student";
	}
	

}
