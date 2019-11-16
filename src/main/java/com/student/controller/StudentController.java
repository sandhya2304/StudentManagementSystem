package com.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.student.dao.StudentRepository;
import com.student.model.Student;

@Controller
@RequestMapping(value="/students")
public class StudentController
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Value("${dir.images}")
	private String imageDir;
	
	
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
	
	@RequestMapping(value="/form",method = RequestMethod.GET)
	public String formStudent(Model model)
	{
		model.addAttribute("student",new Student());
		return "formStudent";
	}

	
	@RequestMapping(value="/saveStudent",method = RequestMethod.POST)
	public String saveStudent(@Valid Student student,BindingResult results,
			@RequestParam("picture")MultipartFile file) throws IllegalStateException, IOException
	{
		if(results.hasErrors())
		{
			return "formStudent";
		}
		if(!(file.isEmpty()))
		{
			student.setPhoto(file.getOriginalFilename());
		}
		studentRepository.save(student);
	
		if(!(file.isEmpty()))
		{
			System.out.println("------------------------------------");
			student.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+student.getId()));
		}
		
		return "redirect:index";
	}
	
	
	
	
	
	
}
