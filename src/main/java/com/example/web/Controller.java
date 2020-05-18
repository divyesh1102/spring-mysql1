package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/{id}")
	@ResponseBody
	public Intern getData(@PathVariable Integer id) {
		System.out.println("hi");
		return new InternDAO().findById(id);
	}
	
	
	@PostMapping("/")
	public void setData(@PathVariable Intern intern) {
		new InternDAO().save(intern);
	}
}
