package com.springboot.thymeleafdemo.dao;

import com.springboot.thymeleafdemo.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    public List<Department> findAll();

    public Department findById(int theId);

}
