package com.springboot.thymeleafdemo.service;

import com.springboot.thymeleafdemo.dao.DepartmentDAO;
import com.springboot.thymeleafdemo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentDAO departmentDAO;

    @Autowired
    public DepartmentServiceImpl(DepartmentDAO theDepartmentDAO) {
        departmentDAO = theDepartmentDAO;
    }

    @Override
    @Transactional
    public List<Department> findAll() {
        return departmentDAO.findAll();
    }

    @Override
    @Transactional
    public Department findById(int theId) {
        Department theDepartment = departmentDAO.findById(theId);
        return theDepartment;
    }
}
