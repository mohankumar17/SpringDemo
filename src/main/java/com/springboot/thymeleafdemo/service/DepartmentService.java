package com.springboot.thymeleafdemo.service;

import com.springboot.thymeleafdemo.entity.Department;

import java.util.List;

public interface DepartmentService {

	public List<Department> findAll();
	
	public Department findById(int theId);
	
}
